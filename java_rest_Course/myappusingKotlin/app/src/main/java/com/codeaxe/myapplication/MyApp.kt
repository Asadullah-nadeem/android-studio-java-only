package com.codeaxe.myapplication

import android.app.Application
import com.stripe.android.PaymentConfiguration

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        PaymentConfiguration.init(
            applicationContext,
            "pk_test_51LZxxaSIp3FnQM9FD8AGWQPA0m7HqvJ55XtEJnGTxX7rDlpgC3qEGHfK3R7OhQOw450dxqt0RlH5WqEFN0o6crp900BtGIDoYF"
        )
    }
}