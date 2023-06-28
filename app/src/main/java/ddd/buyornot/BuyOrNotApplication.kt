package ddd.buyornot

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class BuyOrNotApplication : Application() {

    companion object {
        lateinit var instance: BuyOrNotApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        KakaoSdk.init(this, BuildConfig.KAKAO_OAUTH_HOST_SCHEME)
    }
}
