package ddd.buyornot.home.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.component.data.SnackbarUi
import dagger.hilt.android.lifecycle.HiltViewModel
import ddd.buyornot.data.model.post.PostResult
import ddd.buyornot.data.prefs.SharedPreferenceWrapper
import ddd.buyornot.data.repository.archive.ArchiveRepository
import ddd.buyornot.data.repository.poll.PollRepository
import ddd.buyornot.data.repository.post.PostRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sharedPreferenceWrapper: SharedPreferenceWrapper,
    private val postRepository: PostRepository,
    private val pollRepository: PollRepository,
    private val archiveRepository: ArchiveRepository
) : ViewModel() {

    private var page = 0
    private val count = 20
    val postList: MutableLiveData<List<PostResult>> = MutableLiveData(mutableListOf())
    val profile = sharedPreferenceWrapper.profile

    private val _uiEvent = MutableSharedFlow<SnackbarUi>()
    val uiEvent: SharedFlow<SnackbarUi>
        get() = _uiEvent

    private val _isRefresh = mutableStateOf(false)
    val isRefresh: State<Boolean> = _isRefresh

    fun refresh() {
        viewModelScope.launch {
            _isRefresh.value = true
            fetchPostList()
            _isRefresh.value = false
        }
    }

    suspend fun fetchPostList(init: Boolean = true) {
        viewModelScope.launch {
            val currentList = if (init) {
                mutableListOf()
            } else {
                postList.value?.toMutableList() ?: mutableListOf()
            }
            val newPostList = postRepository.fetchPostList(page, count)?.result

            if (!newPostList.isNullOrEmpty()) {
                currentList.addAll(newPostList)
                postList.postValue(currentList)
                // page++
            }
        }
    }

    suspend fun patchPollChoice(postId: Int, choice: Int) {
        viewModelScope.launch {
            val isSuccess = pollRepository.patchPollChoice(postId, choice)?.isSuccess

            if (isSuccess == true) {
                fetchPostList()
                _uiEvent.emit(SnackbarUi.POLL)
            }
        }
    }

    private suspend fun postArchiveItem(itemId: Int) {
        viewModelScope.launch {
            archiveRepository.postArchiveItem(itemId)
            _uiEvent.emit(SnackbarUi.POST_ARCHIVE)
        }
    }

    private suspend fun patchArchiveItemDelete(itemId: Int) {
        viewModelScope.launch {
            archiveRepository.patchArchiveItemDelete(itemId)
            _uiEvent.emit(SnackbarUi.DELETE_ITEM)
        }
    }

    suspend fun clickLikeButton(isLike: Boolean, itemId: Int) {
        if (isLike) {
            patchArchiveItemDelete(itemId)
        } else {
            postArchiveItem(itemId)
        }
    }
}