package scripts

import org.tribot.script.sdk.Waiting
import org.tribot.script.sdk.script.ScriptConfig
import org.tribot.script.sdk.script.TribotScript


/* Written by IvanEOD 2/17/2023, at 10:50 AM */

/**
 * An interface that should be implemented by all scripts for easy setup and initializing the [Session] object which helps manage script.
 *
 * This interface also provides the [isRandomsAndLoginHandlerEnabled] and [isBreakHandlerEnabled] properties to enable or disable the randoms and login handler and break handler in [ScriptConfig] by [TribotScript.configure].
 * - [isRandomsAndLoginHandlerEnabled] is true by default.
 * - [isBreakHandlerEnabled] is true by default.
 *
 * @see TribotScript
 * @see ScriptConfig
 * @see Session
 */
interface ScriptInterface : TribotScript {

    val isRandomsAndLoginHandlerEnabled get() = true
    val isBreakHandlerEnabled get() = true

    override fun configure(config: ScriptConfig) {
        config.isRandomsAndLoginHandlerEnabled = isRandomsAndLoginHandlerEnabled
        config.isBreakHandlerEnabled = isBreakHandlerEnabled
    }

    override fun execute(args: String) {
        Session.start()
        onStart(args)
        while (Session.isRunning) {
            loop()
            Waiting.wait(100)
        }
    }


    /**
     * Called when the script is started before the loop starts.
     *
     * @param args The arguments passed to the script through the [execute] method.
     */
    fun onStart(args: String)


    /**
     * The script loop called every 100ms while [Session.isRunning] is true.
     *
     */
    fun loop()

}