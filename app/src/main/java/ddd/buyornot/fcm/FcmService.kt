package ddd.buyornot.fcm

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FcmService : FirebaseMessagingService() {

    // TODO: 토큰 값 발급 후 처리
    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    // TODO: 클라우드 메시지 처리
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
    }

}