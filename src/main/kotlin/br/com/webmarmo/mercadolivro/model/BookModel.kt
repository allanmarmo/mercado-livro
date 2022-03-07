package br.com.webmarmo.mercadolivro.model

import br.com.webmarmo.mercadolivro.enums.BookStatus
import br.com.webmarmo.mercadolivro.enums.Errors
import br.com.webmarmo.mercadolivro.exception.BadRequestException
import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "book")
data class BookModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var price: BigDecimal,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerModel? = null

) {
    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null
        /*
        * Bloqueando a alteração quando o status for
        * CANCELADO OU DELETADO
        * field = valor atual
        * Erro: Não é possível alterar um livro com status CANCELADO OU DELETADO.
        * */
        set(value) {
            if (field == BookStatus.CANCELADO || field == BookStatus.DELETADO) {
                throw BadRequestException(Errors.ML102.message.format(field), Errors.ML102.code)
            }
            // Caso seja possível ele altera o valor, ou seja.
            // Vai sobrescrever o valor
            field = value
        }

    constructor(
        id: Int? = null,
        name: String,
        price: BigDecimal,
        customer: CustomerModel? = null,
        status: BookStatus?
    ) : this(id, name, price, customer) {
        this.status = status
    }
}