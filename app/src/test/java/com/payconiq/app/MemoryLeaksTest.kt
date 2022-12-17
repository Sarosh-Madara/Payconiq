package com.payconiq.app

import com.payconiq.app.BaseMemoryLeaksTest
import com.payconiq.app.MemoryLeaksTest
import com.google.common.testing.GcFinalization
import java.lang.AssertionError
import java.lang.Exception
import java.lang.IllegalStateException
import java.lang.RuntimeException
import java.lang.ref.WeakReference
import java.util.*
import java.util.concurrent.Callable

class MemoryLeaksTest : BaseMemoryLeaksTest() {
    // Allow assigning null to potentiallyLeakingCallable to clear the stack's reference on the
    // callable.
    override fun <T> assertNotLeaking(potentiallyLeakingCallable: Callable<T>) {
        var potentiallyLeakingCallable: Callable<T>? = potentiallyLeakingCallable
        val wr: WeakReference<T>
        try {
            wr = WeakReference(potentiallyLeakingCallable!!.call())
            // Make it explicit that the callable isn't reachable from this method's stack, in case it
            // holds a strong reference on the supplied instance.
            potentiallyLeakingCallable = null
        } catch (e: Exception) {
            throw IllegalStateException("encountered an error in the callable", e)
        }
        assertReferentWeaklyReachable(wr)
    }

    companion object {
        private fun <T> assertReferentWeaklyReachable(wr: WeakReference<T>) {
            try {
                GcFinalization.awaitClear(wr)
            } catch (e: RuntimeException) {
                val notWeaklyReachable = wr.get()
                    ?: // Looks like it is weakly reachable after all.
                    return
                throw AssertionError(
                    String.format(
                        Locale.ROOT,
                        "Not true that <%s> is not leaking, encountered an error while attempting to GC it.",
                        notWeaklyReachable
                    ),
                    e
                )
            }
        }
    }
}