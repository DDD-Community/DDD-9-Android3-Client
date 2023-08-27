package ddd.buyornot.upload_item.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ddd.buyornot.data.repository.archive.ArchiveRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UploadItemViewModel @Inject constructor(
    private val archiveRepository: ArchiveRepository
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

    fun onUrlChanged(url: String) {

    }

    fun checkUrl(url: String) {
        viewModelScope.launch {
            runCatching {
                val result = archiveRepository.postArchiveItem(url).getOrNull()

            }
        }
    }
}