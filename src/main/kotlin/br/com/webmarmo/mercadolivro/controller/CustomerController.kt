package br.com.webmarmo.mercadolivro.controller

import br.com.webmarmo.mercadolivro.controller.request.PostCustomerRequest
import br.com.webmarmo.mercadolivro.controller.request.PutCustomerRequest
import br.com.webmarmo.mercadolivro.controller.response.CustomerResponde
import br.com.webmarmo.mercadolivro.extension.toCustomerModel
import br.com.webmarmo.mercadolivro.extension.toResponse
import br.com.webmarmo.mercadolivro.security.UserCanOnlyAccessTheirOwnResource
import br.com.webmarmo.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("customer")
class CustomerController(
    val customerService: CustomerService
) {

    @GetMapping
    fun getAll(@RequestParam name: String?): List<CustomerResponde> {
        return customerService.getAll(name = name).map { it.toResponse() }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid customer: PostCustomerRequest) {
        customerService.create(customer.toCustomerModel())
    }

    @GetMapping("/{id}")
    @UserCanOnlyAccessTheirOwnResource
    fun getCustomer(@PathVariable id: Int): CustomerResponde {
        return customerService.findById(id = id).toResponse()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody @Valid customer: PutCustomerRequest) {
        val customerSaved = customerService.findById(id = id)
        customerService.update(customer.toCustomerModel(customerSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int) {
        customerService.delete(id = id)
    }
}