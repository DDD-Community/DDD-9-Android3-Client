package ddd.buyornot.add_vote.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ddd.buyornot.data.model.post.PostRequest
import ddd.buyornot.data.repository.post.PostRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNewVoteViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {

    companion object {
        const val ITEM_COUNT = 2
        const val TITLE_MAX_LENGTH = 30
        const val DESCRIPTION_MAX_LENGTH = 200
    }

    private val _isValidPost: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val isValidPost = _isValidPost.asSharedFlow()

    fun checkPostValidation(
        title: String,
        description: String,
        vararg itemUrls: String,
    ) {
        viewModelScope.launch {
            _isValidPost.emit(
                title.isNotEmpty()
                        && title.length <= TITLE_MAX_LENGTH
                        && description.length <= DESCRIPTION_MAX_LENGTH
                        && itemUrls.size >= ITEM_COUNT
            )
        }
    }

    fun postVote(
        title: String,
        description: String,
        hideVote: Boolean,
        vararg itemUrls: String,
    ) {
        viewModelScope.launch {
            val request = PostRequest(
                title = title,
                content = description,
                publicStatus = (if (hideVote) PublicStatus.PRIVATE else PublicStatus.PUBLIC).name,
                itemUrls = itemUrls.toList()
            )
            postRepository.postNewPost(request)
        }
    }

    fun saveVote(
        title: String,
        description: String,
        hideVote: Boolean,
        vararg itemUrls: String,
    ) {
        viewModelScope.launch {

        }
    }
}

enum class PublicStatus {
    PUBLIC, PRIVATE
}