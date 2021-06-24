package IOT

class Account(val user: String,private val password: String) {
    lateinit var home:Master
    init {
        this.login(this.user, this.password)
    }
    private var isAuth = false
    fun login(user:String, password: String){
        this.isAuth = true
    }
    fun logout(){
        this.isAuth = false
    }
    fun isAuthenticated(): Boolean{
        return  this.isAuth
    }
    fun renameHome(name: String){
        this.home.name = name
    }
}