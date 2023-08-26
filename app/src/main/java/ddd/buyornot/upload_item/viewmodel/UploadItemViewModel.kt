package ddd.buyornot.upload_item.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ddd.buyornot.data.service.PostService
import javax.inject.Inject


@HiltViewModel
class UploadItemViewModel @Inject constructor(
    private val postService: PostService
) : ViewModel() {

    fun uploadItem(
        title: String,
        description: String,
        hideVote: Boolean,
        vararg itemUrls: String,
    ) {
        /*val request = PostRequest(
            title = title,
            content = description,
            publicStatus = (if (hideVote) PublicStatus.PRIVATE else PublicStatus.PUBLIC).name,
            itemUrls = itemUrls.toList()
        )
        postService.item(request)*/
    }
}