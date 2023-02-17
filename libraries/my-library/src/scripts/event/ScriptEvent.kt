package scripts.event

import java.util.concurrent.ConcurrentLinkedDeque


/* Written by IvanEOD 2/17/2023, at 10:53 AM */

/**
 * Script event
 *
 * Base class for all script events.
 *
 * This class is not meant to be used directly, but rather extended by other classes.
 *
 * @see scripts.event.ConnectedScriptEvent
 * @see scripts.event.RunnableEvent
 *
 * @param T Listener type
 */
abstract class ScriptEvent<T : Any> @PublishedApi internal constructor() {
    internal val listeners = ConcurrentLinkedDeque<T>()

    /**
     * Internal method for [ConnectedScriptEvent] used in [addListener] to connect the listener that triggers this event.
     *
     */
    internal open fun connect() {}

    /**
     * Internal method for [ConnectedScriptEvent] used in [removeListener] to disconnect the listener that triggers this event.
     *
     */
    internal open fun disconnect() {}

    /**
     * Add listener to the event, and connects the event if it is not already connected.
     *
     * @param listener
     * @return The listener that was added. Provides reference to a listener created inline for later use with [removeListener].
     */
    fun addListener(listener: T): T {
        connect()
        listeners.add(listener)
        return listener
    }

    /**
     * Remove the listener from the event, and disconnects the event if no more listeners are left.
     *
     * @param listener
     */
    fun removeListener(listener: T) {
        listeners.remove(listener)
        // Disconnects the event if no more listeners are left
        if (listeners.isEmpty()) disconnect()
    }

    /**
     * Plus assign operator
     *
     * Allows adding listeners by <code>event += listener</code>
     *
     * @param listener
     */
    operator fun plusAssign(listener: T) {
        addListener(listener)
    }

    /**
     * Minus assign operator
     *
     * Allows removing listeners by <code>event -= listener</code>
     *
     * @param listener
     */
    operator fun minusAssign(listener: T) {
        removeListener(listener)
    }


    /**
     * Fires the event by calling the block on each listener
     *
     * @param block
     * @receiver The listener that is being fired
     */
    fun fire(block: T.() -> Unit) {
        listeners.forEach { it.block() }
    }

}
