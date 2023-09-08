package ddd.buyornot.postpage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.component.PostItem
import dagger.hilt.android.lifecycle.HiltViewModel
import ddd.buyornot.data.model.post.PostRequest
import ddd.buyornot.data.model.post.PostResult
import ddd.buyornot.data.repository.archive.ArchiveRepository
import ddd.buyornot.data.repository.post.PostRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShareViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val archiveRepository: ArchiveRepository
) : ViewModel() {

    private val _postList = MutableLiveData<List<PostItem>>()
    val postList: LiveData<List<PostItem>>
        get() = _postList

    var currentPost = PostRequest()

    private val _selectedPost = MutableLiveData<PostItem?>(null)
    val selectedPost: LiveData<PostItem?>
        get() = _selectedPost

    var sharedItemUrl: String = ""

    var sharedItemImageUrl: String? = null
    var currentItemImageUrl: String? = null

    fun setCurrentPostTitle(title: String) {
        currentPost = currentPost.copy(title = title)
    }

    fun setCurrentPostContent(content: String) {
        currentPost = currentPost.copy(content = content)
    }

    fun setCurrentPostPublic(isPrivate: Boolean) {
        currentPost =
            currentPost.copy(publicStatus = if (isPrivate) PostResult.PublicStatus.PRIVATE.name else PostResult.PublicStatus.PUBLIC.name)
    }

    private fun setCurrentPostIsPublish(isPublish: Boolean) {
        currentPost = currentPost.copy(published = isPublish)
    }

    fun setSelectedPost(postItem: PostItem?) {
        _selectedPost.postValue(postItem)
    }

    suspend fun fetchPostList() {
        viewModelScope.launch {
            val postResult = postRepository.fetchTemporaryPost()?.result ?: return@launch
            val postItemList = postResult.map { it ->
                PostItem(
                    postId = it.id,
                    imageUrl = it.pollItemResponseList?.first()?.imgUrl,
                    title = it.title,
                    content = it.content,
                    isPublic = it.publicStatus == PostResult.PublicStatus.PUBLIC
                )
            }
            _postList.postValue(postItemList)
        }
    }

    // 임시 저장된 투표에서 title, itemUrl이 없는 경우는 예외처리가 필요할 듯
    suspend fun fetchTemporaryPost(postId: Int) {
        viewModelScope.launch {
            val postResult = postRepository.fetchPost(postId)?.result ?: return@launch
            setCurrentPostTitle(postResult.title ?: throw NullPointerException())
            setCurrentPostContent(postResult.content ?: "")
            addPostItemUrl(postResult.pollItemResponseList?.first()?.itemUrl ?: throw NullPointerException())
            currentItemImageUrl = postResult.pollItemResponseList?.first()?.imgUrl
        }
    }

    private fun addPostItemUrl(itemUrl: String) {
        val currentItemUrls = currentPost.itemUrls?.toMutableList() ?: mutableListOf()
        currentItemUrls.add(itemUrl)

        currentPost = currentPost.copy(itemUrls = currentItemUrls.toList())
    }

    suspend fun postNewPost() {
        viewModelScope.launch {
            addPostItemUrl(sharedItemUrl)
            setCurrentPostIsPublish(false)
            postRepository.postNewPost(currentPost)
        }
    }

    suspend fun postPublishPost() {
        viewModelScope.launch {
            selectedPost.value?.postId?.let {
                addPostItemUrl(sharedItemUrl)
                setCurrentPostIsPublish(true)
                postRepository.postPublishPost(it, currentPost)
            }
        }
    }

    suspend fun postArchiveItem() {
        viewModelScope.launch {
            if (sharedItemUrl.isNotEmpty()) {
                archiveRepository.postArchiveItem(sharedItemUrl).let {
                    if (it?.isSuccess == true) {
                        sharedItemImageUrl = it.result?.imgUrl
                    }
                }
            }
        }
    }
}