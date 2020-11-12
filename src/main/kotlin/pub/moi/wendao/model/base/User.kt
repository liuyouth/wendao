package pub.moi.wendao.model.base

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class User(@Id @GeneratedValue(strategy = GenerationType.AUTO)
                var id: Long = 0L,
                var number: Long = 0L, // 编号
                var name: String? = "",
                var nikeName: String = "",
                var avatar: String? = "",//头像
                var phone: String? = "",
                var email: String? = "",
                var address: String? = "",
                var session_key: String?="",//微信sesion字段
                var openid: String?="",//微信openid
                var stepNum: Long?=0,//当天走路步数
                @JsonIgnore
                var pwd: String = "",
                var salt: String = "",
                var token: String = "",
//                var imei: String = "",
//                var createdByAppVersion: String = "",
//                var brand: String = "",
//                var netWork: String = "",
//                var screen: String = "",
//                var fromWhere: String = "",
//                var os: String = "",
//                var model: String = "",
                var points: Long = 0L//积分总数


//                        authorization : token | wehjwehjkwhekjqh_1 | uuid产生token_用户编号
//                        appVersion : 版本号
//                os: 系统版本
//                from: 终端 Android / iOS / Web / Web-Admin / H5
//                screen : 屏幕尺寸
//                netWork: 网络类型 3G 4G Wifi
//brand : 手机品牌
//imei : 唯一标示
)