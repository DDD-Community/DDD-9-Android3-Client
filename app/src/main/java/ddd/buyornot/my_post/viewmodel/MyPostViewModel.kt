package ddd.buyornot.my_post.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ddd.buyornot.data.model.post.PostResult
import ddd.buyornot.data.repository.post.PostRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPostViewModel @Inject constructor(
    private val postRepository: PostRepository
): ViewModel() {

    private var onGoingPage = 0
    private var closedPage = 0
    private val count = 20

    val onGoingPostList: MutableLiveData<List<PostResult>> = MutableLiveData(mutableListOf())
    val closedPostList: MutableLiveData<List<PostResult>> = MutableLiveData(mutableListOf())

    suspend fun fetchOnGoingPostList(page: Int, count: Int) {
        viewModelScope.launch {
            val newPostList = postRepository.fetchOnGoingPostList(page, count)?.result
            if (!newPostList.isNullOrEmpty()) {
                val currentList = onGoingPostList.value ?: emptyList()
                onGoingPostList.value?.toMutableList()?.addAll(newPostList)
                onGoingPostList.postValue(currentList)
                onGoingPage++
            }
        }
    }

    suspend fun fetchClosedPostList(page: Int, count: Int) {
        viewModelScope.launch {
            val newPostList = postRepository.fetchClosedPostList(page, count)?.result
            if (!newPostList.isNullOrEmpty()) {
                val currentList = closedPostList.value ?: emptyList()
                closedPostList.value?.toMutableList()?.addAll(newPostList)
                closedPostList.postValue(currentList)
                onGoingPage++
            }
        }
    }
}