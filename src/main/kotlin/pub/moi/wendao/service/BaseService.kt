package pub.moi.wendao.service

import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import java.util.*

interface BaseService<T, ID> {

    fun findAll(var1: Pageable): Page<T>?
    fun findAll(): List<T>?
    fun findAll(var1: Sort): List<T>?
    fun findAllById(var1: Iterable<ID>): List<T>?
    fun <S : T?> saveAll(var1: Iterable<S>): List<S>?
    fun flush()

    fun <S : T?> saveAndFlush(var1: S): S

    fun deleteInBatch(var1: Iterable<T>)

    fun deleteAllInBatch()

    fun getOne(var1: ID): T

    fun <S : T?> findAll(var1: Example<S>): List<S>?

    fun <S : T?> findAll(var1: Example<S>, var2: Sort): List<S>?

    fun <S : T?> save(var1: S): S

    fun findById(var1: ID): Optional<T>?

    fun existsById(var1: ID): Boolean


    fun count(): Long

    fun deleteById(var1: ID)

    fun delete(var1: T)

    fun deleteAll(var1: Iterable<T>)

    fun deleteAll()
}