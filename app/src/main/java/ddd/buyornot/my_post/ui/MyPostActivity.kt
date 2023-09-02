package ddd.buyornot.my_post.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.BDSAppBar
import com.ddd.component.BDSBottomSheetTextList
import com.ddd.component.BDSConfirmDialog
import com.ddd.component.BDSFilledButton
import com.ddd.component.BDSIconButton
import com.ddd.component.BDSImage
import com.ddd.component.BDSOutlinedButton
import com.ddd.component.BDSTab
import com.ddd.component.BDSText
import com.ddd.component.R
import com.ddd.component.data.BDSTextData
import com.ddd.component.theme.BDSColor.Black
import com.ddd.component.theme.BDSColor.Red
import com.ddd.component.theme.BDSColor.SlateGray800
import com.ddd.component.theme.BDSColor.SlateGray900
import dagger.hilt.android.AndroidEntryPoint
import ddd.buyornot.add_vote.ui.AddNewVoteActivity
import ddd.buyornot.data.model.post.PostResult
import ddd.buyornot.home.BDSHomeCard
import ddd.buyornot.my_post.viewmodel.MyPostViewModel
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MyPostActivity : ComponentActivity() {

    private val viewModel by viewModels<MyPostViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var selectedTabIndex by remember { mutableStateOf(0) }

            val onGoingPostList by viewModel.onGoingPostList.observeAsState(emptyList())
            val closedPostList by viewModel.closedPostList.observeAsState(emptyList())

            // TODO: data fetch 로직 추가
            // TODO: paging 추가

            var postList by remember {
                mutableStateOf(
                    when (selectedTabIndex) {
                        0 -> onGoingPostList
                        1 -> closedPostList
                        else -> emptyList()
                    }
                )
            }

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
                MyPostScreen(
                    postList,
                    selectedTabIndex,
                    viewModel
                )
            }
        }
    }
}

// TODO: selectTabIndex, selectOptionIndex State로 전환

@ExperimentalMaterial3Api
@Composable
fun MyPostScreen(
    postList: List<PostResult>,
    selectedTabIndex: Int,
    viewModel: MyPostViewModel
) {
    val context = LocalContext.current

    var openBottomSheet by remember { mutableStateOf(false) }
    var openBottomDialog by remember { mutableStateOf(false) }
    var selectedPostId: Int? = null
    var selectedOption: Int? = null

    val scope = rememberCoroutineScope()

    if (postList.isEmpty()) {
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
                    onClick = { context.startActivity(Intent(context, AddNewVoteActivity::class.java)) },
                    text = "투표 만들러 가기"
                )
            }
        }
    } else {
        LazyColumn {
            items(postList) { postResult ->
                BDSHomeCard(
                    post = postResult,
                    isMyPost = true,
                    onClickDots = {
                        selectedPostId = postResult.id
                        openBottomSheet = true
                    }
                )
            }
        }
    }

    if (openBottomSheet) {
        BDSBottomSheetTextList(
            onDismissRequest = { openBottomSheet = false },
            title = "투표 옵션",
            texts = when (selectedTabIndex) {
                0 -> listOf(
                    BDSTextData(
                        text = "투표 공유하기",
                        modifier = Modifier.clickable { selectedOption = 0 },
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = SlateGray800,
                    ),
                    BDSTextData(
                        text = "답변 그만받기",
                        modifier = Modifier.clickable {
                            openBottomDialog = true
                            selectedOption = 1
                        },
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = SlateGray800
                    ),
                    BDSTextData(
                        text = "투표 삭제하기",
                        modifier = Modifier.clickable {
                            openBottomDialog = true
                            selectedOption = 2
                        },
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Red
                    ),
                )

                1 -> listOf(
                    BDSTextData(
                        text = "투표 삭제하기",
                        modifier = Modifier.clickable {
                            openBottomDialog = true
                            selectedOption = 2
                        },
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Red,
                    )
                )

                else -> emptyList()
            },
            onClickRightIcon = { openBottomSheet = false }
        )
    }

    if (openBottomDialog) {
        val sheetState: SheetState = rememberModalBottomSheetState()
        BDSConfirmDialog(
            onDismissRequest = { openBottomDialog = false },
            title = when (selectedOption) {
                1 -> "투표 답변을 그만 받으시겠어요?"
                2 -> "투표를 삭제할까요?"
                else -> ""
            },
            subTitle = when (selectedOption) {
                1 -> "답변 받기를 중단하면, 다시 답변을 받을 수 없어요."
                2 -> "삭제된 투표는 불러올 수 없어요"
                else -> ""
            },
            sheetState = sheetState,
            cancelButton = {
                BDSOutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        scope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                openBottomDialog = false
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
                        scope.launch {
                            selectedPostId?.let {
                                when (selectedOption) {
                                    1 -> viewModel.patchPostFinish(it)
                                    2 -> viewModel.patchPostDelete(it)
                                    else -> {}
                                }
                            }
                            sheetState.hide()
                        }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                openBottomDialog = false
                            }
                        }
                    },
                    text = when (selectedOption) {
                        1 -> "그만 받기"
                        2 -> "삭제"
                        else -> ""
                    },
                    containerColor = when (selectedOption) {
                        2 -> Red
                        else -> Black
                    },
                )
            }
        )
    }
}