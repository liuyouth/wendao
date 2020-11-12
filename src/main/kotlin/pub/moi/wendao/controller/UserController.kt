package pub.moi.wendao.controller

import pub.moi.wendao.authorization.annotation.Authorization
import pub.moi.wendao.authorization.annotation.CurrentUser
import pub.moi.wendao.authorization.manager.TokenManager
import pub.moi.wendao.authorization.manager.impl.RedisTokenManager
import pub.moi.wendao.model.base.User
import pub.moi.wendao.db.UserRepository
import pub.moi.wendao.model.base.RBuilder
import pub.moi.wendao.model.base.Result
import pub.moi.wendao.net.DataManager
import pub.moi.wendao.net.WxResult
import pub.moi.wendao.utils.WXDecrypt

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.StringUtils.isEmpty
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    lateinit var repository: UserRepository
    @Autowired
    lateinit var tokenManager: TokenManager
    @Autowired
    lateinit var redisTokenManager: RedisTokenManager



    val dataManager: DataManager = DataManager()
    /**
     * 注册接口
     */
    @PostMapping("/register")
    fun register(@CurrentUser loginUser: User, @RequestBody user: User): Result<User> {
        if (isEmpty(user.nikeName))
            return RBuilder.failed("昵称不能为空")
        if (isEmpty(user.pwd))
            return RBuilder.failed("密码不能为空")
        if (user.pwd.length < 6 || user.pwd.length > 20)
            return RBuilder.failed("密码长度为6-20位")
//        if (isEmpty(user.email))
//            return RBuilder.failed("邮箱不能为空")
        if (user.id != 0L)
            return RBuilder.failed("非法请求")
//        //基础验证通过
        var lUser = loginUser
        var result: Result<User>? = null
        dataManager.register(user).subscribe({
            result = if (it.getCode() == 200) {
                it.getData()?.let {
                    loginUser.number = it.number
                    loginUser.nikeName = it.nikeName
                    loginUser.address = it.address
                    loginUser.avatar = it.avatar
                    loginUser.phone = it.phone
                    loginUser.name = it.name
                    loginUser.email = it.email
                    lUser = repository.save(loginUser)
                }
                it
            } else it
        }, { t ->
            result = RBuilder.failed(t.message!!)
        })

        return result?.let {
            it.setData(lUser)
        } ?: RBuilder.failed("")
    }

    /**
     * 登陆接口 token 强制刷新
     * 静态页面测试使用
     */
    @ResponseBody
    @PostMapping("/locallogin")
    fun _locallogin(): Result<User> {

        var result: Result<User>? = null

        dataManager.login("宇轩", "123456.").subscribe({ t: Result<User>? ->
            t?.let {
                if (t.getCode() == 200) {
                    val user = repository.findByNumber(t.getData()!!.number)
                    if (user == null) {
                        t.getData()!!.id = 0L
//                        val group = ToDoGroup()
//                        group.name = "小透明"
//                        group.info = "我是默认的清单不可删除哦"
//                        group.user = repository.save(t.getData())
//                        toDoGroupRepository.save(group)
//                        RBuilder.seccess(group.user)
                        RBuilder.seccess(repository.save(t.getData()))
                    } else {
                        it.getData()?.let {
                            user.token = it.token
                            repository.save(user)
                        }
                        result = RBuilder.seccess(user)
                    }


                } else result = t
            }
        }, { t ->
            result = RBuilder.failed(t.message!!)
        })

        return if (null != result) result!! else RBuilder.failed("未知原因！")
    }

    class loginBody {
        var userName: String? = ""
        var passWord: String? = ""
        var code: String? = ""
    }

    /**
     * 登陆接口 token 强制刷新
     */
    @ResponseBody
    @PostMapping("/login")
    fun loginr(@RequestBody body: loginBody): Result<User> {

        return login(body.userName!!, body.passWord!!, body.code!!)

    }

    /**
     * 登陆接口 token 强制刷新
     */
    @ResponseBody
    @PostMapping("/loginForm")
    fun login(@RequestParam userName: String, @RequestParam passWord: String, code: String): Result<User> {
        if (isEmpty(userName))
            return RBuilder.failed("账号不能为空")
        if (isEmpty(passWord))
            return RBuilder.failed("密码不能为空")
        var result: Result<User>? = null

        dataManager.login(userName, passWord).subscribe({ t: Result<User>? ->
            t?.let {
                if (t.getCode() == 200) {
                    var user = repository.findByNumber(t.getData()!!.number)
                    // 用户不存在 初始化诗句
                    if (user == null) {

                        user = repository.save(t.getData())!!
                        t.getData()!!.id = 0L
//                        val group = ToDoGroup()
//                        group.name = "小透明"
//                        group.info = "我是默认的清单不可删除哦"
//                        group.user = user
//                        toDoGroupRepository.save(group)


//                        var task = Task()
//                        task.title = "学习"
//                        task.info = "每天的积累"
//                        task.user = user
//                        taskRepository.save(task)

//                        task = Task()
//                        task.title = "运动"
//                        task.info = "每天的进步"
//                        task.user = user
//                        taskRepository.save(task)
//
//                        task = Task()
//                        task.title = "饮食"
//                        task.info = "每天的改善"
//                        task.user = user
//                        taskRepository.save(task)

                        RBuilder.seccess(user)
                    } else {
                        it.getData()?.let {
                            user.token = it.token
                            repository.save(user)
                        }
                        result = RBuilder.seccess(user)
                    }
                    //如果有code 表示是从微信小程序过来的
                    if (code.isNotEmpty())
                        dataManager.wxLogin(code).subscribe({ t: WxResult ->
                            if (!t.session_key.isNullOrEmpty()) {
                                user.openid = t.openid
                                user.session_key = t.session_key
                                repository.save(user)
                                result = RBuilder.seccess(user)
                            }
                        })

                } else result = t
            }
        }, { t ->
            result = RBuilder.failed(t.message!!)
        })

        return if (null != result) result!! else RBuilder.failed("未知原因！")
    }

    /**
     * token 获取基本信息
     */
    @GetMapping("/show")
    @Authorization
    @ResponseBody
    fun get(@CurrentUser user: User?): Result<User> {
        println("weeeeeee " + user)
        user?.let {
            val mUser = repository.findByNumber(user.number)
            return RBuilder.seccess(mUser)
        }
        return RBuilder.failed("暂时无法获取！")

    }
    @Authorization
    @PutMapping("/")
    fun update(@CurrentUser user: User, @RequestBody body: User): Result<User> {

        body.id = repository.findByNumber(body.number!!).id
        return RBuilder.seccess(repository.save(body))
    }
    class saveStepNum{
        var encryptedData:String ?=""
        var iv:String ?=""
        var session_key:String ?=""
    }
//    @Authorization
//    @PostMapping("/saveStepNum")
//    fun saveStepNum(@CurrentUser user: User, @RequestBody body: saveStepNum): Result<User> {
//        println()
//        var d =  WXDecrypt.decrypt(body.encryptedData,body.session_key,body.iv);
//        user.stepNum =d.step
//        var dd = dayStepRepository.findByTime(d.timestamp)
//        if (dd==null)
//            dd = DayStep(0,user.number,d.step,d.step,d.timestamp,"");
//        dd.wxStep = d.step
//        dd.count = d.step
//        println("ddd"+dd.toString())
//        dayStepRepository.save(dd)
//        return RBuilder.seccess(repository.save(user))
////        return RBuilder.failed("dddd")
//    }

    /**
     * admin 接口 直接获取 某个用户信息
     */
    @GetMapping("/show/id/{id}")
    @ResponseBody
    fun get(@PathVariable("id") id: String): Result<User> {
        println(id.toLong().toString() + "id")
        println(repository.findOne(id.toLong()))
        return RBuilder.seccess(repository.findOne(id.toLong()))
    }


    @GetMapping("/show/{name}")
    @ResponseBody
    fun get2(@PathVariable("name") name: Long): Result<User> {

        return RBuilder.seccess(repository.findByNumber(name))
    }

    @GetMapping("/search/{name}")
    @ResponseBody
    fun search(@PathVariable("name") name: String): Result<List<User>> {

        return RBuilder.seccess(repository.findByNameLike("%" + name + "%"))
    }


//    private fun getOnlyNumber(): Long {
//        var num = getNumber(7)
//        val us = repository.findByNumber(num)
//        if (us == null) {
//            return num
//        } else return getOnlyNumber()
//
//    }
//
//    private fun getNumber(i: Int): Long {
//        return when (i) {
//            7 -> getNumberByRange(100000, 999999)
//            8 -> getNumberByRange(1000000, 9999999)
//            9 -> getNumberByRange(10000000, 99999999)
//            else -> 1782782638
//        }
//
//    }

//    private fun getNumberByRange(min: Int, max: Int): Long {
//        return min + (Random().nextDouble() * (max - min)).toLong()
//    }
}



