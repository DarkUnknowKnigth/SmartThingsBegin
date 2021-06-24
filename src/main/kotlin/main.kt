import IOT.Account
import IOT.IOT
import IOT.Master

fun main(args: Array<String>) {
    println("SmartThings initialized")
    val account = Account("Dany", "12345678")
    if (account.isAuthenticated()){
        println("Logged as ${account.user}")
    }
    val velvet = IOT("LG VELVET","WIFI","66:F5:AA:C9:AA")
    val samsungTv = IOT("SMART TV SAMSUNG","WIFI","66:F1:1A:79:AA")
    val miBox = IOT("MI BOX XIAOMI","WIFI","55:61:1A:D9:AA")
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
    account.renameHome("Danys SmartHome")
    println(account.home.name)
    account.logout()
}
