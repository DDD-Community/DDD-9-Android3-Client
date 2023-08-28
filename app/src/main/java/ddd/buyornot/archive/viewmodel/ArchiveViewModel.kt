package ddd.buyornot.archive.viewmodel

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

    val archiveItemList = MutableLiveData<List<ArchiveItem>>()
    val likedItemList = MutableLiveData<List<ArchiveItem>>()

    suspend fun fetchArchiveItemList() {
        viewModelScope.launch {
            val currentItemList = archiveItemList.value ?: emptyList()
            val newItemList = archiveRepository.fetchPostList(savedPage, count)?.result?.map { it ->
                ArchiveItem(
                    itemId = it.itemId,
                    imageUrl = it.imgUrl,
                    brand = it.brand,
                    name = it.itemName,
                    discount = it.discountedPrice,
                    price = it.originalPrice,
                    liked = it.liked
                )
            }

            if (!newItemList.isNullOrEmpty()) {
                currentItemList.toMutableList().addAll(newItemList)
                archiveItemList.postValue(currentItemList)
                savedPage++
            }
        }
    }

    suspend fun fetchLikedArchiveItemList() {
        viewModelScope.launch {
            val currentItemList = likedItemList.value ?: emptyList()
            val newItemList = archiveRepository.fetchPostLikedList(likedPage, count)?.result?.map { it ->
                ArchiveItem(
                    itemId = it.itemId,
                    imageUrl = it.imgUrl,
                    brand = it.brand,
                    name = it.itemName,
                    discount = it.discountedPrice,
                    price = it.originalPrice,
                    liked = it.liked
                )
            }

            if (!newItemList.isNullOrEmpty()) {
                currentItemList.toMutableList().addAll(newItemList)
                archiveItemList.postValue(currentItemList)
                likedPage++
            }
        }
    }

    suspend fun patchArchiveItemDelete(archiveItemList: List<ArchiveItem>) {
        viewModelScope.launch {
            val deleteArchiveReq = DeleteArchiveReq(ids = archiveItemList.mapNotNull { it.itemId })
            archiveRepository.patchArchiveItemDelete(deleteArchiveReq = deleteArchiveReq)
        }
    }

    suspend fun patchArchiveItemLike(archiveItem: ArchiveItem) {
        viewModelScope.launch {
            archiveItem.itemId?.let {
                val result = archiveRepository.patchArchiveItemLike(it)?.result?.liked ?: return@launch
                archiveItem.liked = result
            }
        }
    }
}