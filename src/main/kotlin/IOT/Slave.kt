package IOT

import Devices.SmartPhone

interface Slave {
    companion object AppList{
        val VALID_STEAMING_APPS = listOf<String>("Youtube","Facebook","Twitch","Pornhub","Chrome")
        val VALID_MUSIC_APPS = listOf<String>("Deezer","Spotify","PlayMusic","AppleMusic")
    }
    fun mediaReceiver(url: String, app: String, device: SmartPhone): Unit
}