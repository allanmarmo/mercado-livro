package br.com.webmarmo.mercadolivro.repository;

import br.com.webmarmo.mercadolivro.model.CustomerModel
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<CustomerModel, Int> {

    fun findByNameContaining(name: String): List<CustomerModel>
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): CustomerModel?
}