import Devices.SmartPhone
import Devices.SmartSpeaker
import Devices.SmartTV
import IOT.Account
import IOT.IOT
import IOT.Master
import kotlinx.coroutines.*


fun main(args: Array<String>)= runBlocking() {
    println("SmartThings initialized")

    println("Enter user name")
    val user = readLine()
    println("Enter password")
    val pass = readLine()

    val account = Account(user!!, pass!!)

    var name: String? = null
    try {
        println("Ingrese el nombre del dispositivo")
        name = readLine()
    } catch (e: Exception){
        println("Ingrese un nombre valido")
        name="Sin nombre"
    }

    val jobBelvet: Deferred<IOT> = async(start = CoroutineStart.LAZY) { connectSmartPhone(20L, "Iphone", "WIFI", "66:F1:1A:79:AA") }
    val jobSamsungTv: Deferred<IOT> = async(start = CoroutineStart.LAZY) { connectSmartTV(10L,"SMART TV SAMSUNG","WIFI","66:F1:1A:79:AA")}
    val jobMiBox = async(start = CoroutineStart.LAZY) {connectSmartSpeaker(40L,"MI BOX XIAOMI","WIFI","55:61:1A:D9:AA")}
    val home = Master("SMART HOME")
    account.home = home

    lateinit var velvet: SmartPhone
    lateinit var samsungTv:SmartTV
    lateinit var miBox: SmartSpeaker

    home.pairedDevices()
    //home.removeDevice(miBox)

    jobBelvet.start()
    jobSamsungTv.start()
    jobMiBox.start()

    try {
         if (!jobBelvet.isCompleted)jobBelvet.await()
         velvet = jobBelvet.getCompleted() as SmartPhone
    }catch(e:Exception){
        println(e.message)
    }finally {
        if (jobBelvet.isCompleted)
            home.addDevice(velvet)
        else
            println("Something went wrong!")
    }

    try {
        if (!jobSamsungTv.isCompleted)jobSamsungTv.await()
        samsungTv = jobSamsungTv.getCompleted() as SmartTV
    }catch(e:Exception){
        println(e.message)
    }finally {
        if(jobSamsungTv.isCompleted)
            home.addDevice(samsungTv)
        else
            println("Something went wrong!")
    }

    try {
        if (!jobMiBox.isCompleted)jobMiBox.await()
        miBox = jobMiBox.getCompleted() as SmartSpeaker
    }catch(e:Exception){
        println(e.message)
    }finally {
        if(jobMiBox.isCompleted)
            home.addDevice(miBox)
        else
            println("Something went wrong!")
    }

    println("Hi ${account.user}, there is your SmartHome: ${account.home.name} information")
    account.home.showDevicesInfo()
    account.home.setControl(velvet.name)
    account.home.controlAt.volumeControl(true)
    println(account.home.controlAt.info())
    account.renameHome("SmartHome")
    println(account.home.name)
    account.logout()
}

suspend fun connectSmartPhone(_time:Long, _name:String, _connectionType:String, _macAddres:String):SmartPhone{
    repeat(100){ i->
        println("Connecting with SmartPhone $_name $i% ...")
        delay(_time)
    }

    return SmartPhone(_name, _connectionType, _macAddres)
}

suspend fun connectSmartTV(_time:Long, _name:String, _connectionType:String, _macAddres:String):SmartTV{
    repeat(100){ i->
        println("Connecting with SmartTV $_name $i% ...")
        delay(_time)
    }

    return SmartTV(_name, _connectionType, _macAddres)
}

suspend fun connectSmartSpeaker(_time:Long, _name:String, _connectionType:String, _macAddres:String):SmartSpeaker{
    repeat(100){ i->
        println("Connecting with SmartSpeaker $_name $i% ...")
        delay(_time)
    }

    return SmartSpeaker(_name, _connectionType, _macAddres)
}