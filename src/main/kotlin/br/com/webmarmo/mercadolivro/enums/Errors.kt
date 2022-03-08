package br.com.webmarmo.mercadolivro.enums

enum class Errors(val code: String, val message: String) {

    ML001(code = "ML-001", message = "Invalid Request"),
    ML101(code = "ML-101", message = "Book [ %s ] not exists"),
    ML102(code = "ML-102", message = "Connot update book with status [ %s ]"),
    ML201(code = "ML-201", message = "Customer [ %s ] not exists")
}