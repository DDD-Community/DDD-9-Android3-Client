package ddd.buyornot.archive.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.component.ArchiveItem
import com.ddd.component.data.SnackbarUi
import dagger.hilt.android.lifecycle.HiltViewModel
import ddd.buyornot.data.model.archive.DeleteArchiveReq
import ddd.buyornot.data.repository.archive.ArchiveRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArchiveViewModel @Inject constructor(
    private val archiveRepository: ArchiveRepository
) : ViewModel() {

    private var page = 0
    private val count = 20

    val archiveItemList = MutableLiveData<MutableList<ArchiveItem>>()

    private val _uiEvent = MutableSharedFlow<SnackbarUi>()
    val uiEvent : SharedFlow<SnackbarUi>
        get() = _uiEvent

    suspend fun fetchArchiveItemList(init: Boolean = false) {
        viewModelScope.launch {
            val currentItemList = if (init) {
                page = 0
                mutableListOf()
            } else {
                archiveItemList.value ?: mutableListOf()
            }

            val newItemList = archiveRepository.fetchArchiveList(page, count)?.result?.map { it ->
                ArchiveItem(
                    id = it.id,
                    itemId = it.itemId,
                    itemUrl = it.itemUrl,
                    imageUrl = it.imgUrl,
                    brand = it.brand,
                    name = it.itemName,
                    discount = it.discountedRate,
                    price = it.originalPrice,
                    liked = it.liked
                )
            }

            if (!newItemList.isNullOrEmpty()) {
                currentItemList.addAll(newItemList)
                archiveItemList.postValue(currentItemList)
                page++
            }
        }
    }

    suspend fun patchArchiveItemDelete(archiveItemList: List<ArchiveItem>) {
        viewModelScope.launch {
            val deleteArchiveReq = DeleteArchiveReq(ids = archiveItemList.mapNotNull { it.id })
            archiveRepository.patchArchiveItemDelete(deleteArchiveReq = deleteArchiveReq)?.run {
                if (isSuccess) {
                    fetchArchiveItemList(true)
                    _uiEvent.emit(SnackbarUi.DELETE_ITEM)
                }
            }
            _uiEvent.emit(SnackbarUi.DELETE_ITEM)
        }
    }
}