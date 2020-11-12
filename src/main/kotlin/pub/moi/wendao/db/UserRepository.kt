package pub.moi.wendao.db

import pub.moi.wendao.model.base.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface UserRepository : CrudRepository<User, Long> {
    fun findByName(name: String): User
    fun findByNameLike(name: String): List<User>
    fun findByNikeName(nikeName: String): User
    fun findByNumber(num:Long):User
//    fun findByImei(imei:String):User
//    @Query("from User u where u.firstName=:name")
//    fun findUser(@Param("name") name: String): List<User>
}