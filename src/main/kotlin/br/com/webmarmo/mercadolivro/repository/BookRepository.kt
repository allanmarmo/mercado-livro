package br.com.webmarmo.mercadolivro.repository

import br.com.webmarmo.mercadolivro.enums.BookStatus
import br.com.webmarmo.mercadolivro.model.BookModel
import br.com.webmarmo.mercadolivro.model.CustomerModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<BookModel, Int> {
    fun findByStatus(status: BookStatus, pageable: Pageable): Page<BookModel>
    fun findByCustomer(customer: CustomerModel): List<BookModel>

//    fun findAll(pageable: Pageable): Page<BookModel>

}