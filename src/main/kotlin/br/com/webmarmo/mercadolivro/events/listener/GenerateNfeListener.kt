package br.com.webmarmo.mercadolivro.events.listener

import br.com.webmarmo.mercadolivro.events.PurchaseEvent
import br.com.webmarmo.mercadolivro.service.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.*

@Component
class GenerateNfeListener(
    private val purchaseService: PurchaseService
) {

    @Async
    @EventListener
    fun listen(purchaseEvent: PurchaseEvent) {
        println("Gerando Nfe")
        val nfe = UUID.randomUUID().toString()
        val purchaseModel = purchaseEvent.purchaseModel.copy(nfe = nfe)
        purchaseService.update(purchaseModel)
    }
}