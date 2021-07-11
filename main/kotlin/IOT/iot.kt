package IOT

open class IOT(val name: String, private val connectionType: String, private val macAddress: String) {
    private val on = false
    private var volume = 5
    private var connected = false
        set(value) {
            field = value
        }
    fun isConnected(): String {
        return if(this.connected) "Connected" else "Desconnected"
    }
    fun info(): String{
        return "NAME: ${this.name}, CONNECTED BY: ${this.connectionType}, MAC: ${this.macAddress}, STATUS:${this.isConnected()}, VOLUME: ${this.volume}"
    }
    fun turnOff(){
        println("Turning Off")
        this.connected = false
    }
    fun verifyConnection (master: Master): Boolean{
        for ( device in master.pairedDevices())
        {
            if(device.macAddress == this.macAddress){
                println("Connected with ${master.name}")
                this.connected = true
                return true
            }
        }
        println("Not Connected with ${master.name}")
        return false
    }
    fun volumeControl(kind:Boolean){
        if(kind) this.volume.inc() else this.volume.dec()
        print("Volume: ${if (kind) "UP" else "DOWN"}")
    }
}