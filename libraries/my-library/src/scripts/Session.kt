package scripts

import org.tribot.script.sdk.ScriptListening
import scripts.event.RunnableEvent
import scripts.event.ScriptEvent


/* Written by IvanEOD 2/17/2023, at 10:51 AM */

/**
 * An object that handles the script session.
 *
 * @see ScriptInterface
 */
object Session {

    var isRunning = false
        private set


    /**
     * Start the script session, this is called by the [ScriptInterface.onStart] method and should not be called manually.
     * This method will add a listener to the TRiBot [ScriptListening] class to stop the session when the script ends, cancelling the while loop calling [ScriptInterface.loop].
     *
     */
    fun start() {
        ScriptListening.addEndingListener { stop() }
        isRunning = true

    }

    fun stop() {
        isRunning = false
    }


    /**
     * A [ScriptEvent] that is triggered when the script is ending.
     *
     * @see ScriptEvent
     * @see scripts.event.RunnableEvent
     * @see ScriptListening.addPreEndingListener
     *
     */
    val onScriptEnding: RunnableEvent = ScriptEvent(
        ScriptListening::addPreEndingListener,
        ScriptListening::removePreEndingListener,
    )




}