package ddd.buyornot

import android.app.Application

class BuyOrNotApplication : Application() {

    companion object {
        lateinit var instance: BuyOrNotApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
