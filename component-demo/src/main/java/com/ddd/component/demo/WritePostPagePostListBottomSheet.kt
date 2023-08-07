package com.ddd.component.demo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ddd.component.BDSBottomSheet
import com.ddd.component.BDSBottomSheetHeader
import com.ddd.component.BDSBottomSheetHorizontalDualButton
import com.ddd.component.BDSBottomSheetSingleButton
import com.ddd.component.BDSButtonInnerPadding
import com.ddd.component.BDSFilledButton
import com.ddd.component.BDSOutlinedButton
import com.ddd.component.BDSPostCard
import com.ddd.component.BDSText
import com.ddd.component.PostItem
import com.ddd.component.R
import com.ddd.component.theme.BDSColor

@Composable
fun WritePostPagePostListBottomSheet(
    onDismissRequest: () -> Unit
) {
    val postItemList = listOf(
        PostItem(
            imageUrl = "https://images.unsplash.com/photo-1661956600655-e772b2b97db4?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80",
            title = "이제 레인부츠 사려는데 어떤걸 ...",
            isPublic = false
        ),
        PostItem(
            imageUrl = "https://images.unsplash.com/photo-1661956600655-e772b2b97db4?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80",
            title = "이제 레인부츠 사려는데 어떤걸 ...",
            isPublic = false
        ),
        PostItem(
            imageUrl = "https://images.unsplash.com/photo-1661956600655-e772b2b97db4?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80",
            title = "이제 레인부츠 사려는데 어떤걸 ...",
            isPublic = false
        ),
        PostItem(
            imageUrl = "https://images.unsplash.com/photo-1661956600655-e772b2b97db4?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80",
            title = "이제 레인부츠 사려는데 어떤걸 ...",
            isPublic = false
        ),
        PostItem(
            imageUrl = "https://images.unsplash.com/photo-1661956600655-e772b2b97db4?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80",
            title = "이제 레인부츠 사려는데 어떤걸 ...",
            isPublic = false
        ),
        PostItem(
            imageUrl = "https://images.unsplash.com/photo-1661956600655-e772b2b97db4?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80",
            title = "이제 레인부츠 사려는데 어떤걸 ...",
            isPublic = false
        ),
        PostItem(
            imageUrl = "https://images.unsplash.com/photo-1661956600655-e772b2b97db4?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80",
            title = "이제 레인부츠 사려는데 어떤걸 ...",
            isPublic = false
        ),
        PostItem(
            imageUrl = "https://images.unsplash.com/photo-1661956600655-e772b2b97db4?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80",
            title = "이제 레인부츠 사려는데 어떤걸 ...",
            isPublic = false
        ),
        PostItem(
            imageUrl = "https://images.unsplash.com/photo-1661956600655-e772b2b97db4?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80",
            title = "이제 레인부츠 사려는데 어떤걸 ...",
            isPublic = false
        ),
        PostItem(
            imageUrl = "https://images.unsplash.com/photo-1661956600655-e772b2b97db4?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80",
            title = "이제 레인부츠 사려는데 어떤걸 ...",
            isPublic = false
        ),
        PostItem(
            imageUrl = "https://images.unsplash.com/photo-1661956600655-e772b2b97db4?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80",
            title = "이제 레인부츠 사려는데 어떤걸 ...",
            isPublic = false
        )
    )

    // val postItemList: List<PostItem> = emptyList()

    BDSBottomSheet(
        onDismissRequest = onDismissRequest,
        headerContent = {
            BDSBottomSheetHeader(
                center = {
                    BDSText(
                        text = "투표 올리기",
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = BDSColor.SlateGray900
                    )
                },
                right = {

                }
            )
        },
        bodyContent = {
            if (postItemList.isNullOrEmpty()) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(23.5.dp))
                    Icon(painter = painterResource(id = R.drawable.ic_content_empty), contentDescription = "")
                    Spacer(modifier = Modifier.height(23.5.dp))
                    BDSText(
                        text = "앗, 만들어진 투표가 없어요!",
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = BDSColor.SlateGray900
                    )
                    Spacer(modifier = Modifier.height(23.5.dp))
                }
            } else {
                LazyColumn() {
                    items(postItemList) { postItem ->
                        BDSPostCard(postItem = postItem)
                    }
                }
            }
        },
        bottomContent = {
            if (postItemList.isNotEmpty()) {
                BDSBottomSheetHorizontalDualButton(
                    acceptButton = {
                        BDSOutlinedButton(
                            onClick = { /*TODO*/ }, text = "새 투표 만들기",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            contentPadding = BDSButtonInnerPadding.MEDIUM,
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                        )
                    },
                    cancelButton = {
                        BDSFilledButton(
                            onClick = { /*TODO*/ }, text = "상품 추가하기",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            contentPadding = BDSButtonInnerPadding.MEDIUM,
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            // enabled = 상품이 선택됐을 때 true
                        )
                    }
                )
            } else {
                BDSBottomSheetSingleButton(
                    button = {
                        BDSFilledButton(
                            onClick = { /*TODO*/ }, text = "새 투표 만들기",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            contentPadding = BDSButtonInnerPadding.MEDIUM,
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                        )
                    }
                )
            }
        }
    )
}