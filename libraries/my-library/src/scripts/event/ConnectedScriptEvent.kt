package scripts.event

import org.tribot.script.sdk.ScriptListening

/* Written by IvanEOD 2/17/2023, at 10:53 AM */

/**
 * Connected script event
 *
 * - Wraps a TRiBot or other event in a [ScriptEvent] to allow for custom events and TRiBot events to be implemented the same way.
 *
 * - This class is not meant to be created directly, but rather created through builder methods such as [scripts.event.ScriptEvent]
 *
 * @param T The type of listener
 * @property connector The method that connects this event's trigger to the existing event
 * @property disconnector The method that disconnects this event's trigger from the existing event
 *
 * @param createTriggerListener A lambda with the [ScriptEventTriggerProvider] as receiver to give access to the [ScriptEventTriggerProvider.withEachListener] and [ScriptEventTriggerProvider.onEachListener] methods to be used to create the listener that will trigger this event.
 *
 *
 */
open class ConnectedScriptEvent<T : Any> internal constructor(
    private val connector: (T) -> Unit,
    private val disconnector: (T) -> Unit,
    createTriggerListener: ScriptEventTriggerProvider<T>.() -> T
) : ScriptEvent<T>() {
    private var connected = false
    private var setup = false

    // This is so we can create a listener to be connected to an existing (TRiBot) event, that will fire all the event listeners.
    // This way custom events and TRiBot events can be implemented the same way.
    private val listenerProvider = object : ScriptEventTriggerProvider<T> {
        override fun withEachListener(block: T.() -> Unit) {
            listeners.forEach { it.block() }
        }

        override fun onEachListener(block: (T) -> Unit) {
            listeners.forEach { block(it) }
        }
    }

    private val listener: T by lazy { listenerProvider.createTriggerListener() }

    // Disconnecting the listener that fires all the event listeners
    override fun disconnect() {
        disconnector(listener)
        connected = false
        // Disconnected but remains setup so disconnect isn't added to the script ending listener multiple times
    }

    // Connecting the listener that fires all the event listeners
    override fun connect() {
        if (connected) return
        connected = true
        connector(listener)
        // Only add the listener to disconnect this event when the script ends on the first connection
        if (!setup) {
            setup = true
            ScriptListening.addEndingListener { disconnect() }
        }
    }
}