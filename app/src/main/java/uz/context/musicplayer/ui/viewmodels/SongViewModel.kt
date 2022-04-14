package uz.context.musicplayer.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.context.musicplayer.data.entities.Song
import uz.context.musicplayer.exoplayer.MusicService
import uz.context.musicplayer.exoplayer.MusicServiceConnection
import uz.context.musicplayer.exoplayer.currentPlaybackPosition
import javax.inject.Inject

@HiltViewModel
class SongViewModel @Inject constructor(
    musicServiceConnection: MusicServiceConnection
): ViewModel() {

    private val playbackState = musicServiceConnection.playbackState

    private val _curSongDuration = MutableLiveData<Long>()
    val curSongDuration: LiveData<Long> = _curSongDuration

    private val _curPlayerPosition = MutableLiveData<Long>()
    val curPlayerPosition: LiveData<Long> = _curPlayerPosition

    init {
        updateCurPlayerPosition()
    }

    private fun updateCurPlayerPosition() {
        viewModelScope.launch {
            while (true) {
                val position = playbackState.value?.currentPlaybackPosition
                if (curPlayerPosition.value != position) {
                    _curPlayerPosition.postValue(position!!)
                    _curSongDuration.postValue(MusicService.currentSongDuration)
                }
                delay(100L)
            }
        }
    }
}