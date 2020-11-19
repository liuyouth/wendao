package pub.moi.wendao.service

import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

open class BaseServiceImpl<T, ID, R : JpaRepository<T, ID>>(var repository: R)
    : BaseService<T, ID> {

    override fun findAll(var1: Pageable): Page<T>? {
        return repository.findAll(var1)
    }

    override fun findAll(): List<T>? {
        return repository.findAll()
    }

    override fun findAll(var1: Sort): List<T>? {
        return repository.findAll(var1)
    }

    override fun findAllById(var1: Iterable<ID>): List<T>? {
        return repository.findAllById(var1)
    }

    override fun <S : T?> saveAll(var1: Iterable<S>): List<S>? {
        return repository.saveAll(var1)
    }

    override fun flush() {
        repository.flush()
    }

    override fun <S : T?> saveAndFlush(var1: S): S {
        return repository.saveAndFlush(var1)
    }

    override fun deleteInBatch(var1: Iterable<T>) {
        return repository.deleteInBatch(var1)
    }

    override fun deleteAllInBatch() {
        return deleteAllInBatch()
    }

    override fun getOne(var1: ID): T {
        return repository.getOne(var1)
    }

    override fun <S : T?> findAll(var1: Example<S>): List<S>? {
        return repository.findAll(var1)
    }

    override fun <S : T?> findAll(var1: Example<S>, var2: Sort): List<S>? {
        return repository.findAll(var1,var2)
    }

    override fun <S : T?> save(var1: S): S {
        return repository.save(var1)
    }

    override fun findById(var1: ID): Optional<T>? {
     return repository.findById(var1)
    }

    override fun existsById(var1: ID): Boolean {
        return repository.existsById(var1)
    }



    override fun count(): Long {
return repository.count()
    }

    override fun deleteById(var1: ID) {
        return repository.deleteById(var1)
    }

    override fun delete(var1: T) {
        return repository.delete(var1)
    }

    override fun deleteAll(var1: Iterable<T>) {
        return repository.deleteAll(var1)
    }

    override fun deleteAll() {
        return repository.deleteAll()
    }


}