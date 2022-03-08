package br.com.webmarmo.mercadolivro.service

import br.com.webmarmo.mercadolivro.enums.CustomerStatus
import br.com.webmarmo.mercadolivro.enums.Errors
import br.com.webmarmo.mercadolivro.exception.NotFoundException
import br.com.webmarmo.mercadolivro.model.CustomerModel
import br.com.webmarmo.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService
) {

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customerRepository.findByNameContaining(it)
        }
        return customerRepository.findAll().toList()
    }

    fun create(customer: CustomerModel) {
        customerRepository.save(customer)
    }

    fun findById(id: Int): CustomerModel {
        return customerRepository.findById(id)
            .orElseThrow { NotFoundException(Errors.ML201.message.format(id), Errors.ML201.code) } //.get()
    }

    fun update(customer: CustomerModel) {
        if (!customerRepository.existsById(customer.id!!)) {
            throw Exception()
        }

        customerRepository.save(customer)
    }

    fun delete(id: Int) {
        // Delete o customer e os livros amarrados ao customoer
        val customer = findById(id)
        bookService.deleteByCustomer(customer)

        customer.status = CustomerStatus.INATIVO

        // Erro ao deletar,
        // a foreign key constraint fails (`mercadolivro`.`book`,
        // CONSTRAINT `book_ibfk_1` FOREIGN KEY (`customer_id`)
        // REFERENCES `customer` (`id`))
        // Obs: Não pode ser deletar por ser um id unico.
        // FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
        // Pode usar o casquete type

        // Delete sem deletar os livros
        /*if (!customerRepository.existsById(id)) {
            throw Exception()
        }
        customerRepository.deleteById(id)
        */

        customerRepository.save(customer)
    }

    fun emailAvailable(email: String): Boolean {
        return !customerRepository.existsByEmail(email)
    }
}