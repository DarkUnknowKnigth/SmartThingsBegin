import Devices.SmartPhone
import Devices.SmartSpeaker
import Devices.SmartTV
import IOT.Account
import IOT.IOT
import IOT.Master
import java.util.Date
import java.text.SimpleDateFormat

fun main(args: Array<String>) {
    val now = Date()
    val formatDate = SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz")
    println("SmartThings initialized ${formatDate.format(now)}")
    val account = Account("Dany", "12345678")
    if (account.isAuthenticated()){
        println("Logged as ${account.user}")
    }
    var name: String? = null
    try {
        println("Ingrese el nombre del dispositivo")
        name = readLine()
    } catch (e: Exception){
        println("Ingrese un nombre valido")
        name="Sin nombre"
    }
    val velvet = SmartPhone(name!!,"WIFI","66:F5:AA:C9:AA")
    val samsungTv = SmartTV("SMART TV SAMSUNG","WIFI","66:F1:1A:79:AA")
    val miBox = SmartSpeaker("MI BOX XIAOMI","WIFI","55:61:1A:D9:AA")
    val home = Master("SMART HOME")
    account.home = home
    home.pairedDevices()
    home.addDevice(velvet)
    home.removeDevice(miBox)
    home.addDevice(miBox)
    home.addDevice(samsungTv)
    println("Hi ${account.user}, there is your SmartHome: ${account.home.name} information")
    account.home.showDevicesInfo()
    account.home.setControl(samsungTv.name)
    account.home.controlAt.volumeControl(true)
    println(account.home.controlAt.info())
    account.renameHome("SmartHome")
    println(account.home.name)
    account.logout()
}
