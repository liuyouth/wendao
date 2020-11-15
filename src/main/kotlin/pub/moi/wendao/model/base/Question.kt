package pub.moi.wendao.model.base

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class Question(@Id @GeneratedValue(strategy = GenerationType.AUTO)
                    var id: Long = 0L
                    , var number: Long = 0L// 编号
                    , var title: String? = ""// 问题标题
                    , var info: String? = ""// 问题详细
                    , var avatar: String? = ""//附图
                    , var postUserNo: Long = 0L// 提交用户编号
                    , var postTime: Long = 0L // 提交时间
                    , var lastUpDateTime: Long = 0L // 最后修改时间
)