package scripts.event

import java.util.*

/* Written by IvanEOD 2/17/2023, at 10:53 AM */
sealed interface ScriptEventListener: EventListener

fun interface ScriptEventListener1<T1: Any>: ScriptEventListener {
    fun onEvent(t1: T1)
}

fun interface ScriptEventListener2<T1: Any, T2: Any>: ScriptEventListener {
    fun onEvent(t1: T1, t2: T2)
}

fun interface ScriptEventListener3<T1: Any, T2: Any, T3: Any>: ScriptEventListener {
    fun onEvent(t1: T1, t2: T2, t3: T3)
}

fun interface ScriptEventListener4<T1: Any, T2: Any, T3: Any, T4: Any>: ScriptEventListener {
    fun onEvent(t1: T1, t2: T2, t3: T3, t4: T4)
}

fun interface ScriptEventListener5<T1: Any, T2: Any, T3: Any, T4: Any, T5: Any>: ScriptEventListener {
    fun onEvent(t1: T1, t2: T2, t3: T3, t4: T4, t5: T5)
}