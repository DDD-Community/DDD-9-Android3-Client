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

    private val _currentPost = MutableLiveData(PostRequest())
    val currentPost: LiveData<PostRequest>
        get() = _currentPost

    private val _selectedPost = MutableLiveData<PostItem?>(null)
    val selectedPost: LiveData<PostItem?>
        get() = _selectedPost

    var sharedItemUrl: String = ""

    fun setCurrentPostTitle(title: String) {
        _currentPost.run {
            postValue(value?.copy(title = title))
        }
    }

    fun setCurrentPostContent(content: String) {
        _currentPost.run {
            postValue(value?.copy(content = content))
        }
    }

    fun setSelectedPost(postItem: PostItem?) {
        _selectedPost.postValue(postItem)
    }

    suspend fun fetchPostList() {
        viewModelScope.launch {
            val postResult = postRepository.fetchTemporaryPost()?.result ?: return@launch
            val postItemList = postResult.map { it -> PostItem(
                postId = it.id,
                imageUrl = it.pollItemResponseList?.first()?.imgUrl,
                title = it.content,
                isPublic = it.publicStatus == PostResult.PublicStatus.PUBLIC // publicStatus로 변경 예정
            ) }
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
        }
    }

    private fun addPostItemUrl(itemUrl: String) {
        _currentPost.run {
            val currentItemUrls = value?.itemUrls?.toMutableList()
            currentItemUrls?.add(itemUrl)
            postValue(value?.copy(itemUrls = currentItemUrls?.toList()))
        }
    }

    suspend fun postPublishPost(postId: Int) {
        addPostItemUrl(sharedItemUrl)
        viewModelScope.launch {
            currentPost.value?.let {
                postRepository.postPublishPost(postId, it)
            }
        }
    }

    suspend fun postArchiveItem() {
        viewModelScope.launch {
            if (sharedItemUrl.isNotEmpty()) {
                val result = archiveRepository.postArchiveItem(sharedItemUrl)
            }
        }
    }
}