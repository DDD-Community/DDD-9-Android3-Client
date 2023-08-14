package ddd.buyornot.postpage.bottomsheet

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ddd.component.BDSConfirmDialog
import com.ddd.component.BDSFilledButton
import com.ddd.component.BDSOutlinedButton

@Composable
@ExperimentalMaterial3Api
fun WritePostPageSaveAlertBottomSheet(
    onDismissRequest: () -> Unit,
) {
    BDSConfirmDialog(
        onDismissRequest = onDismissRequest,
        title = "상품을 삭제할까요?",
        subTitle = "선택하신 상품을 정말 삭제하시겠어요?",
        cancelButton = {
            BDSOutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
                text = "취소"
            )
        },
        acceptButton = {
            BDSFilledButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
                text = "로그아웃",
                containerColor = Color.Red
            )
        }
    )
}