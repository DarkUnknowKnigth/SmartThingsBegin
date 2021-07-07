package Devices

import IOT.IOT
import IOT.Slave

class SmartSpeaker(
    name: String, connectionType: String="bluethoot", macAddress: String
): IOT(name,connectionType,macAddress), Slave {
    private var song:String?= null;
    private lateinit var app:String
    private lateinit var device: SmartPhone
    init {
        this.song="src/main/resources/example.mp3"
    }
    override fun mediaReceiver(url: String, app: String, device: SmartPhone) {
        this.app = app
        this.device = device
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
            "PLAY" -> println("Playing $song on $app from ${device.name}")
            "PAUSE" ->  println("Pausing $song on $app from ${device.name}")
            "STOP" ->  println("Stopping $song on $app from ${device.name}")
        }
    }
}