package br.com.webmarmo.mercadolivro.controller.response

import br.com.webmarmo.mercadolivro.enums.CustomerStatus

data class CustomerResponde(
    var id: Int? = null,
    var name: String,
    var email: String,
    var status: CustomerStatus
)
