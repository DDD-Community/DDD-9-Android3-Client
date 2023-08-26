package ddd.buyornot.add_vote.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.BDSButton
import com.ddd.component.BDSSwitch
import com.ddd.component.BDSText
import com.ddd.component.theme.BDSColor

@Composable
fun VoteBottomContent(
    modifier: Modifier = Modifier,
    checked: Boolean = false,
    savedCount: Int = 0,
    postButtonEnabled: Boolean = false,
    onCheckedChange: (Boolean) -> Unit,
    onClickSave: () -> Unit,
    onClickPost: (Boolean) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BDSSwitch(
                checked = checked,
                onCheckedChange = {
                    onCheckedChange(it)
                },
            )
            BDSText(
                modifier = Modifier.padding(start = 8.dp),
                text = "비공개 (링크를 받은 친구만 투표 가능)",
                fontSize = 14.sp,
                color = BDSColor.SlateGray900,
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            BDSButton(
                text = "임시저장 $savedCount",
                fontSize = 14.sp,
                contentColor = BDSColor.SlateGray900,
                containerColor = Color.Transparent,
            ) {
                onClickSave()
            }
            BDSButton(
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 13.dp),
                modifier = Modifier.fillMaxWidth(),
                text = "글 등록하기",
                fontSize = 16.sp,
            ) {
                onClickPost(checked)
            }
        }
    }
}
