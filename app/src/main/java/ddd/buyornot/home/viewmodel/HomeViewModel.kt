package ddd.buyornot.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ddd.buyornot.data.model.post.PostResult
import ddd.buyornot.data.repository.poll.PollRepository
import ddd.buyornot.data.repository.post.PostRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val pollRepository: PollRepository
) : ViewModel() {

    private var page = 0
    private val count = 20
    val postList: MutableLiveData<List<PostResult>> = MutableLiveData(mutableListOf())

    fun fetchPostList() {
        viewModelScope.launch {
            val newPostList = postRepository.fetchPostList(page, count)?.result
            if (!newPostList.isNullOrEmpty()) {
                val currentList = postList.value ?: emptyList()
                postList.value?.toMutableList()?.addAll(newPostList)
                postList.postValue(currentList)
                page++
            }
        }
    }

    fun patchPollChoice(postId: Int, choice: Int) {
        viewModelScope.launch {
            pollRepository.patchPollChoice(postId, choice)
        }
    }
}