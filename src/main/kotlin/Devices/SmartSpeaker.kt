package Devices

import IOT.IOT
import IOT.Slave

class SmartSpeaker(
    name: String, connectionType: String="bluethoot", macAddress: String
): IOT(name,connectionType,macAddress), Slave {
    override fun mediaReceiver(url: String, app: String, device: SmartPhone) {
        if(url.isNotEmpty()){
            println("Media Receiver incoming from ${device.name}")
            println("Playing $app on $url")
        }
        else {
            println("Url must exist")
        }
    }
}