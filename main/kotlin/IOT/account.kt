package IOT

val users= mapOf<String,String>("Dany" to "12345678", "Carlos" to "password")

class Account(val user: String,private val password: String) {
    lateinit var home:Master
    init {
        this.login(this.user, this.password)
    }
    private var isAuth = false
    fun login(user:String, password: String){
        try{
            if(users.containsKey(user)) {
               if (password == users[user])
                this.isAuth = true
                else throw IllegalArgumentException()

            }else{ throw IllegalArgumentException() }
        }catch(e:Exception){
            println("authentication of user failed")
            this.isAuth = false
        }finally {
            if(isAuthenticated()) println("Welcome $user")
            else println("Welcome stranger")
        }
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