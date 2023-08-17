package ddd.buyornot.postpage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.component.PostItem
import dagger.hilt.android.lifecycle.HiltViewModel
import ddd.buyornot.data.repository.post.PostRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShareViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {

    private val _postList = MutableLiveData<List<PostItem>>()
    val postList: LiveData<List<PostItem>>
        get() = _postList

    fun fetchPostList() {
        viewModelScope.launch {
            val postResult = postRepository.fetchTemporaryPost()?.result ?: return@launch
            postResult.map { it -> PostItem(
                imageUrl = it.pollItemResponseList?.first()?.imgUrl,
                title = it.content,
                isPublic = false // publicStatus로 변경 예정
            ) }
        }
    }

}