package ddd.buyornot.archive.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddd.component.ArchiveItem
import dagger.hilt.android.lifecycle.HiltViewModel
import ddd.buyornot.data.model.archive.DeleteArchiveReq
import ddd.buyornot.data.repository.archive.ArchiveRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArchiveViewModel @Inject constructor(
    private val archiveRepository: ArchiveRepository
) : ViewModel() {

    private var savedPage = 0
    private var likedPage = 0
    private val count = 20

    val tabIndex = MutableLiveData<Int>()

    val savedItemList = MutableLiveData<MutableList<ArchiveItem>>()
    val likedItemList = MutableLiveData<MutableList<ArchiveItem>>()

    suspend fun fetchSavedItemList(init: Boolean = false) {
        viewModelScope.launch {
            val currentItemList = if (init) {
                savedPage = 0
                mutableListOf()
            } else {
                savedItemList.value ?: mutableListOf()
            }

            val newItemList = archiveRepository.fetchPostList(savedPage, count)?.result?.map { it ->
                ArchiveItem(
                    id = it.id,
                    itemId = it.itemId,
                    imageUrl = it.imgUrl,
                    brand = it.brand,
                    name = it.itemName,
                    discount = it.discountedRate,
                    price = it.originalPrice,
                    liked = it.liked
                )
            }

            Log.d("viewModel", "fetchSavedItemList: ${newItemList?.size}")

            if (!newItemList.isNullOrEmpty()) {
                currentItemList.addAll(newItemList)
                savedItemList.postValue(currentItemList)
                savedPage++
            }
        }
    }

    suspend fun fetchLikedItemList(init: Boolean = false) {
        viewModelScope.launch {
            val currentItemList = if (init) {
                likedPage = 0
                mutableListOf()
            } else {
                likedItemList.value ?: mutableListOf()
            }

            val newItemList = archiveRepository.fetchPostLikedList(likedPage, count)?.result?.map { it ->
                ArchiveItem(
                    id = it.id,
                    itemId = it.itemId,
                    imageUrl = it.imgUrl,
                    brand = it.brand,
                    name = it.itemName,
                    discount = it.discountedRate,
                    price = it.originalPrice,
                    liked = it.liked
                )
            }
            Log.d("viewModel", "fetchLikedItemList: ${newItemList?.size}")

            if (!newItemList.isNullOrEmpty()) {
                currentItemList.addAll(newItemList)
                likedItemList.postValue(currentItemList)
                likedPage++
            }
        }
    }

    suspend fun patchArchiveItemDelete(archiveItemList: List<ArchiveItem>) {
        viewModelScope.launch {
            val deleteArchiveReq = DeleteArchiveReq(ids = archiveItemList.mapNotNull { it.id })
            val success = archiveRepository.patchArchiveItemDelete(deleteArchiveReq = deleteArchiveReq)?.isSuccess
            if (success == true) {
                when (tabIndex.value) {
                    0 -> fetchLikedItemList(true)
                    1 -> fetchSavedItemList(true)
                }
            }
        }
    }

    suspend fun patchArchiveItemLike(archiveItem: ArchiveItem) {
        viewModelScope.launch {
            archiveItem.id?.let {
                val success = archiveRepository.patchArchiveItemLike(it)?.isSuccess
                if (success != null) {
                    when (tabIndex.value) {
                        0 -> fetchLikedItemList(true)
                        1 -> fetchSavedItemList(true)
                    }
                }
            }
        }
    }

    fun setTabIndex(index: Int) {
        tabIndex.value = index
    }
}