package com.ddd.component.demo

import androidx.compose.runtime.Composable
import com.ddd.component.BDSPostCard
import com.ddd.component.PostItem

/*
    나중에 PostList 등으로 변경
 */
@Composable
fun PostCardTestScreen() {
    val postItem = PostItem(
        imageUrl = "https://images.unsplash.com/photo-1661956600655-e772b2b97db4?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80",
        title = "이제 레인부츠 사려는데 어떤걸 ...",
        isPublic = false
    )

    BDSPostCard(postItem = postItem)
}