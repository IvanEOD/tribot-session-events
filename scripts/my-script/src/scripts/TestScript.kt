package scripts

import org.tribot.script.sdk.Log
import org.tribot.script.sdk.script.TribotScriptManifest


/* Written by IvanEOD 2/17/2023, at 12:44 PM */
@TribotScriptManifest(name = "TestScript", author = "IvanEOD", category = "A")
class TestScript : ScriptInterface {

    override fun onStart(args: String) {

        Session.onScriptEnding += {
            Log.debug("Script ending!")
        }

    }

    override fun loop() {

    }


}