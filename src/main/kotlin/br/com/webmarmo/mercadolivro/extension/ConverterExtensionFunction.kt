package br.com.webmarmo.mercadolivro.extension

import br.com.webmarmo.mercadolivro.controller.request.PostCustomerRequest
import br.com.webmarmo.mercadolivro.controller.request.PutCustomerRequest
import br.com.webmarmo.mercadolivro.model.CustomerModel

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return CustomerModel(name = this.name, email = this.email)
}

fun PutCustomerRequest.toCustomerModel(id: String): CustomerModel {
    return CustomerModel(id = id, name = this.name, email = this.email)
}