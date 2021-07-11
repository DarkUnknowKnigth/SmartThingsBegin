package Devices

import IOT.IOT
import IOT.Slave

class SmartTV(
    name: String, connectionType: String="wifi", macAddress: String
): IOT(name,connectionType,macAddress), Slave{
    private var videoStatus: String ="STOP"
        get() = videoStatus
        set(value) {
            field = when(value){
                "STOP" -> "STOP"
                "PLAY" -> "PLAY"
                "PAUSE" -> "PAUSE"
                "PREV" -> "PREV"
                "NEXT" -> "NEXT"
                else -> "STOP"
            }
        }
    override fun mediaReceiver(url: String, app: String, device: SmartPhone){
        if(url.isNotEmpty()){
            println("Media Receiver incoming from ${device.name}")
            println("Opening $app on $url")
        }
        else {
            println("Url must exist")
        }
    }
}