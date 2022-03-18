package com.esmaeel.challenge.testUtils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry

/**
 * in case we needed to open activity.
 */
fun <T : Activity> goto(to: Class<T>): ActivityScenario<T> {
    return ActivityScenario.launch(to)
}

/**
 * in case we needed to pass intents into other activities while opening it
 */
fun <T : Activity> goto(to: Class<T>, bundle: Bundle? = null): ActivityScenario<T> {
    val context = InstrumentationRegistry.getInstrumentation().targetContext
    return ActivityScenario.launch<T>(
        Intent(context, to).also {
            bundle?.let(it::putExtras)
        }
    )
}
