package scripts.event

/* Written by IvanEOD 2/17/2023, at 10:53 AM */



/**
 * Script event trigger provider for [ConnectedScriptEvent] to create a listener to trigger an event.
 *
 * @param T
 */
interface ScriptEventTriggerProvider<T : Any> {
    /**
     * Loop the listeners with the listener as the receiver context.
     *
     * @param block
     */
    fun withEachListener(block: T.() -> Unit) {}

    /**
     * Loop the listeners with the listener as the parameter.
     *
     * @param block
     */
    fun onEachListener(block: (T) -> Unit) {}
}