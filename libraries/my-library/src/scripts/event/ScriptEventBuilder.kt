package scripts.event


/* Written by IvanEOD 2/17/2023, at 12:48 PM */


/**
 * Create a [ConnectedScriptEvent]
 *
 * @param T The type of listener the event uses.
 * @param connector Method connecting the new event to the existing event.
 * @param disconnector Method disconnecting the new event from the existing event.
 * @param createTriggerListener Method to create a listener that will be added to the existing event which will trigger the created events listeners.
 *
 * @see ConnectedScriptEvent
 * @see ScriptEventTriggerProvider
 */
inline fun <reified T : Any> ScriptEvent(
    noinline connector: (T) -> Unit,
    noinline disconnector: (T) -> Unit,
    noinline createTriggerListener: ScriptEventTriggerProvider<T>.() -> T
) = ConnectedScriptEvent(connector, disconnector, createTriggerListener)


/**
 * Create a [ScriptEvent] with no arguments
 *
 * Can be used as a custom event or as a [ConnectedScriptEvent] for an event that uses [Runnable] listeners if [connector] and [disconnector] are provided.
 *
 * @param connector (Optional) - For implementing [RunnableEvent] as a [ConnectedScriptEvent]
 * @param disconnector (Optional) - For implementing [RunnableEvent] as a [ConnectedScriptEvent]
 *
 * @see RunnableEvent
 * @see ConnectedScriptEvent
 */
inline fun ScriptEvent(
    noinline connector: (Runnable) -> Unit = {},
    noinline disconnector: (Runnable) -> Unit = {},
) = RunnableEvent(connector, disconnector)

/**
 * Create a [ScriptEvent] with 1 argument
 *
 * @param T1 argument
 */
inline fun <reified T1 : Any> ScriptEvent1(): ScriptEvent<ScriptEventListener1<T1>> =
    object : ScriptEvent<ScriptEventListener1<T1>>() {
        fun addListener(block: (T1) -> Unit) = addListener(ScriptEventListener1(block))
        operator fun plusAssign(block: (T1) -> Unit) {
            addListener(block)
        }

        operator fun invoke(argument: T1) {
            fire { onEvent(argument) }
        }
    }

/**
 * Create a [ScriptEvent] with 2 arguments
 *
 * @param T1 argument1
 * @param T2 argument2
 */
inline fun <reified T1 : Any, reified T2 : Any> ScriptEvent2(): ScriptEvent<ScriptEventListener2<T1, T2>> =
    object : ScriptEvent<ScriptEventListener2<T1, T2>>() {
        fun addListener(block: (T1, T2) -> Unit) = addListener(ScriptEventListener2(block))
        operator fun plusAssign(block: (T1, T2) -> Unit) {
            addListener(block)
        }

        operator fun invoke(argument1: T1, argument2: T2) {
            fire { onEvent(argument1, argument2) }
        }
    }

/**
 * Create a [ScriptEvent] with 3 arguments
 *
 * @param T1 argument1
 * @param T2 argument2
 * @param T3 argument3
 */
inline fun <reified T1 : Any, reified T2 : Any, reified T3 : Any> ScriptEvent3(): ScriptEvent<ScriptEventListener3<T1, T2, T3>> =
    object : ScriptEvent<ScriptEventListener3<T1, T2, T3>>() {
        fun addListener(block: (T1, T2, T3) -> Unit) = addListener(ScriptEventListener3(block))
        operator fun plusAssign(block: (T1, T2, T3) -> Unit) {
            addListener(block)
        }

        operator fun invoke(argument1: T1, argument2: T2, argument3: T3) {
            fire { onEvent(argument1, argument2, argument3) }
        }
    }

/**
 * Create a [ScriptEvent] with 4 arguments
 *
 * @param T1 argument1
 * @param T2 argument2
 * @param T3 argument3
 * @param T4 argument4
 */
inline fun <reified T1 : Any, reified T2 : Any, reified T3 : Any, T4 : Any> ScriptEvent4(): ScriptEvent<ScriptEventListener4<T1, T2, T3, T4>> =
    object : ScriptEvent<ScriptEventListener4<T1, T2, T3, T4>>() {
        fun addListener(block: (T1, T2, T3, T4) -> Unit) = addListener(ScriptEventListener4(block))
        operator fun plusAssign(block: (T1, T2, T3, T4) -> Unit) {
            addListener(block)
        }

        operator fun invoke(argument1: T1, argument2: T2, argument3: T3, argument4: T4) {
            fire { onEvent(argument1, argument2, argument3, argument4) }
        }
    }

/**
 * Create a [ScriptEvent] with 5 arguments
 *
 * @param T1 argument1
 * @param T2 argument2
 * @param T3 argument3
 * @param T4 argument4
 * @param T5 argument5
 */
inline fun <reified T1 : Any, reified T2 : Any, reified T3 : Any, reified T4 : Any, reified T5 : Any> ScriptEvent5(): ScriptEvent<ScriptEventListener5<T1, T2, T3, T4, T5>> =
    object : ScriptEvent<ScriptEventListener5<T1, T2, T3, T4, T5>>() {
        fun addListener(block: (T1, T2, T3, T4, T5) -> Unit) = addListener(ScriptEventListener5(block))
        operator fun plusAssign(block: (T1, T2, T3, T4, T5) -> Unit) {
            addListener(block)
        }

        operator fun invoke(argument1: T1, argument2: T2, argument3: T3, argument4: T4, argument5: T5) {
            fire { onEvent(argument1, argument2, argument3, argument4, argument5) }
        }
    }
