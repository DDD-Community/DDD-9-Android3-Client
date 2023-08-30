package ddd.buyornot.my_post.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.BDSAppBar
import com.ddd.component.BDSFilledButton
import com.ddd.component.BDSIconButton
import com.ddd.component.BDSImage
import com.ddd.component.BDSTab
import com.ddd.component.BDSText
import com.ddd.component.R
import com.ddd.component.theme.BDSColor.SlateGray900
import dagger.hilt.android.AndroidEntryPoint
import ddd.buyornot.data.model.post.PostResult
import ddd.buyornot.home.BDSHomeCard

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MyPostActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var selectedTabIndex by remember { mutableStateOf(0) }

            var postList = listOf<PostResult>()

            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                BDSAppBar(
                    modifier = Modifier
                        .height(54.dp)
                        .fillMaxWidth(),
                    left = {
                        BDSIconButton(resId = R.drawable.ic_back, onClick = { finish() })
                    },
                    title = "내 투표 아카이브"
                )
                BDSTab(
                    titles = listOf("진행중인 투표", "종료된 투표"),
                    selectedTabIndex = selectedTabIndex,
                    onTabSelected = { selectedTabIndex = it }
                )
                MyPostScreen(postList)
                when (selectedTabIndex) {
                    0 -> postList = viewModel.fetchOnGoingPost()
                    1 -> postList = viewModel.fetchClosedPost()
                    else -> {}
                }
            }
        }
    }
}

@Composable
fun MyPostScreen(postList: List<PostResult>) {
    if (postList.isNullOrEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(modifier = Modifier.align(Alignment.Center)) {
                BDSImage(
                    resId = R.drawable.ic_archive_empty,
                    modifier = Modifier.size(150.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                BDSText(
                    text = "앗, 만들어진 투표가 없어요!",
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SlateGray900,
                )
                Spacer(modifier = Modifier.height(54.dp))
                BDSFilledButton(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                    onClick = { /*TODO*/ },
                    text = "투표 만들러 가기"
                )
            }
        }
    } else {
        LazyColumn {
            items(postList) { postResult ->
                BDSHomeCard(
                    post = postResult,
                    patchPollChoice = { /*viewModel::patchPollChoice*/ }
                )
            }
        }
    }
}