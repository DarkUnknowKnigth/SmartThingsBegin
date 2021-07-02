package Devices

import IOT.IOT
import IOT.Slave
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import java.io.File

class SmartSpeaker(
    name: String, connectionType: String="bluethoot", macAddress: String
): IOT(name,connectionType,macAddress), Slave {
    private var song:String = "example.mp3"
    private var media: Media
    private var mediaPlayer: MediaPlayer
    init {
        media = Media(File(this.song).toURI().toString())
        mediaPlayer = MediaPlayer(media)
    }
    override fun mediaReceiver(url: String, app: String, device: SmartPhone) {
        if(url.isNotEmpty()){
            println("Media Receiver incoming from ${device.name}")
            println("Playing $app on $url")
            this.song = url
        }
        else {
            println("Url must exist")
        }
    }
    fun musicControl(action:String){
        when(action){
            "PLAY" -> this.mediaPlayer.play()
            "PAUSE" -> this.mediaPlayer.pause()
            "STOP" -> this.mediaPlayer.stop()
        }
    }
}