package ddd.buyornot.profile.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.ddd.component.BDSConfirmDialog
import com.ddd.component.BDSFilledButton
import com.ddd.component.BDSIconButton
import com.ddd.component.BDSImage
import com.ddd.component.BDSOutlinedButton
import com.ddd.component.BDSText
import com.ddd.component.R
import com.ddd.component.clickableWithoutRipple
import com.ddd.component.theme.BDSColor
import com.ddd.component.theme.BuyOrNotTheme
import dagger.hilt.android.AndroidEntryPoint
import ddd.buyornot.data.repository.login.AuthRepository
import ddd.buyornot.data.util.KakaoLogin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalMaterial3Api
@AndroidEntryPoint
class ProfileActivity : ComponentActivity() {

    @Inject
    lateinit var authRepository: AuthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val kakaoLogin = KakaoLogin(this@ProfileActivity) { token ->
            CoroutineScope(Dispatchers.IO).launch {
                authRepository.logoutRemote(token)
                    .onSuccess { response ->
                        if (response.isSuccess) {
                            finish()
                        } else {
                            handleLogoutError()
                        }
                    } .onFailure {
                        handleLogoutError()
                    }
            }
        }

        setContent {
            BuyOrNotTheme {

                val scope = rememberCoroutineScope()
                var showLogoutDialogState by remember { mutableStateOf(false) }

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(183.dp),
                    ) {
                        BDSImage(
                            resId = R.drawable.bg_profile,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        BDSIconButton(
                            modifier = Modifier.padding(16.dp),
                            resId = R.drawable.ic_back,
                            onClick = { finish() },
                            tint = BDSColor.White
                        )
                    }
                    BDSText(
                        text = "익명의티셔츠234",
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .padding(top = 42.dp, bottom = 34.dp),
                        fontSize = 24.sp,
                        lineHeight = 32.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = BDSColor.SlateGray900
                    )
                    BDSText(
                        text = "내 글 관리",
                        modifier = Modifier.padding(start = 20.dp),
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = BDSColor.SlateGray700
                    )
                    Divider(modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp))
                    BDSText(
                        modifier = Modifier
                            .clickableWithoutRipple { }
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 13.dp),
                        text = "내가 쓴 글",
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = BDSColor.SlateGray800
                    )
                    Spacer(modifier = Modifier.height(42.dp))
                    BDSText(
                        text = "내 정보 관리",
                        modifier = Modifier.padding(start = 20.dp),
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = BDSColor.SlateGray700
                    )
                    Divider(modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp))
                    BDSText(
                        modifier = Modifier
                            .clickableWithoutRipple { showLogoutDialogState = true }
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 13.dp),
                        text = "로그아웃",
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = BDSColor.SlateGray800
                    )
                    BDSText(
                        modifier = Modifier
                            .clickableWithoutRipple { startActivity(Intent(this@ProfileActivity, CancellationActivity::class.java)) }
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 13.dp),
                        text = "계정삭제",
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = BDSColor.Red
                    )
                }
                BDSImage(
                    resId = R.drawable.ic_app_logo_sample,
                    modifier = Modifier
                        .offset(32.dp, 153.dp)
                        .clip(CircleShape)
                        .size(56.dp)
                        .border(2.dp, BDSColor.White, CircleShape)
                )

                if (showLogoutDialogState) {
                    val sheetState: SheetState = rememberModalBottomSheetState()
                    BDSConfirmDialog(
                        onDismissRequest = { showLogoutDialogState = false },
                        title = "계정에서 로그아웃 할까요?",
                        subTitle = "작성한 투표는 잘 보관하고 있을게요",
                        sheetState = sheetState,
                        cancelButton = {
                            BDSOutlinedButton(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    scope.launch {
                                        sheetState.hide()
                                    }.invokeOnCompletion {
                                        if (!sheetState.isVisible) {
                                            showLogoutDialogState = false
                                        }
                                    }
                                },
                                text = "취소"
                            )
                        },
                        acceptButton = {
                            BDSFilledButton(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    kakaoLogin.kakaoLogout()
                                    scope.launch {
                                        sheetState.hide()
                                    }.invokeOnCompletion {
                                        if (!sheetState.isVisible) {
                                            showLogoutDialogState = false
                                        }
                                    }
                                },
                                text = "로그아웃",
                                containerColor = Color.Red
                            )
                        }
                    )
                }
            }
        }
    }

    private fun handleLogoutError() {
        lifecycleScope.launch(Dispatchers.Main) {
            Toast.makeText(this@ProfileActivity, "로그아웃에 실패했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
        }
    }
}