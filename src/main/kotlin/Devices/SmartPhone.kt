package Devices

import IOT.IOT
import IOT.Slave

class SmartPhone(
    name: String, connectionType: String = "bluethoot", macAddress:String
) : IOT(name, connectionType, macAddress) {
    private var app = "none"
        get() = "Current Open ${this.app}"
        set(value) {
            val music = Slave.VALID_MUSIC_APPS.find { it == value }
            val streaming = Slave.VALID_STEAMING_APPS.find { it == value }
            field = if (music != null || streaming !=null ){ value } else "none"
        }
    fun sendVideo(url: String,tv: SmartTV){
        if (this.app != "none"){
            tv.mediaReceiver(url,this.app,this)
            println("Sending: $url from ${this.app} to ${tv.name}")
        }
        else{
            println("Please select a valid streaming app")
        }
    }
    fun sendAudio(url: String,speaker: SmartSpeaker){
        if (this.app != "none"){
            speaker.mediaReceiver(url,this.app,this)
            println("Sending: $url from ${this.app} to ${speaker.name}")
        }
        else{
            println("Please select a valid music app")
        }
    }
}