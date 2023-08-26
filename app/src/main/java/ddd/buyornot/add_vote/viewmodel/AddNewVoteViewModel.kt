package ddd.buyornot.add_vote.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ddd.buyornot.data.service.PostService
import javax.inject.Inject

@HiltViewModel
class AddNewVoteViewModel @Inject constructor(
    val postService: PostService
) : ViewModel() {

    init {

    }
}