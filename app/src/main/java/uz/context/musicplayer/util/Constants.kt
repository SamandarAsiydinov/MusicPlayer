package uz.context.musicplayer.util

import android.content.Context
import android.widget.Toast

object Constants {
    const val SONG_COLLECTION = "songs"
    const val SERVICE_TAG = "MusicService"
    const val NOTIFICATION_ID = 1
    const val NOTIFICATION_CHANNEL_ID = "music"

    const val NETWORK_ERROR = "NETWORK_ERROR"

    const val MEDIA_ROOT_ID = "root_id"
}
fun Context.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}