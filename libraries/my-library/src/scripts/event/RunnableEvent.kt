package scripts.event


/**
 * Runnable event
 *
 * - A no argument event that can be fired manually, or as a [ConnectedScriptEvent] for a TRiBot event that uses [Runnable] listeners.
 *
 * - This class can be created directly or with the builder method [scripts.event.ScriptEvent]
 *
 * @property connector (Optional) - Method that connects this event's trigger to the existing event
 * @property disconnector (Optional) - Method that disconnects this event's trigger from the existing event
 *
 */
class RunnableEvent constructor(
    connector: (Runnable) -> Unit = {},
    disconnector: (Runnable) -> Unit = {},
) : ConnectedScriptEvent<Runnable>(
    connector,
    disconnector,
    createTriggerListener = {
        Runnable { withEachListener { run() } }
    }
) {

    /**
     * Add listener to the event, and connects the event if it is not already connected.
     * This is a convenience method for non-connected no argument events, and adding Runnable listeners to connected events with Kotlin.
     *
     * @param block
     * @return The [Runnable] listener that was created and added. Provides reference to a listener created inline for later use with [removeListener].
     */
    fun addListener(block: () -> Unit) = addListener(Runnable(block))

    /**
     * Plus assign operator version of the convenience method [addListener].
     *
     * @param block
     */
    operator fun plusAssign(block: () -> Unit) {
        addListener(block)
    }

    /**
     * Allows the event to be fired manually, primarily for non-connected events.
     *
     */
    operator fun invoke() {
        fire { run() }
    }

}