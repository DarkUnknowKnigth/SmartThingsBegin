package IOT

class Master(var name: String) {
    private var iots:MutableList<IOT> = mutableListOf()
    private var volume: Int = 5
    private val MAXPAIREDDEVICES = 10
    lateinit var controlAt: IOT
    fun addDevice(iot: IOT){
        if (this.MAXPAIREDDEVICES >= this.iots.size && !iot.verifyConnection(this) ){
            this.iots.add(iot)
            println("Added: ${iot.name}")
        }
        else{
            println("This controller just support ${this.MAXPAIREDDEVICES} devices, please remove one")
        }
    }
    fun removeDevice(iot: IOT){
        if ( iot.verifyConnection(this)){
            this.iots.remove(iot)
            println("removed: ${iot.name}")
            return
        }
        println("Device: ${iot.name} not Fount")
        return
    }
    fun pairedDevices(): MutableList<IOT> {
        return this.iots
    }
    fun showDevicesInfo(): List<String> {
        println("<DEVICES CONNECTED>")
        for (device in this.iots){
            println("${device.info()}")
        }
        return iots.map { iot -> iot.name }
    }
    fun setControl(name: String){
        val device = this.iots.find { iot -> iot.name == name }
        if (device != null ){
            this.controlAt = device
            println("Controlling ${this.controlAt.info()}")
        } else {
            println("Error to assignation")
        }
    }
}