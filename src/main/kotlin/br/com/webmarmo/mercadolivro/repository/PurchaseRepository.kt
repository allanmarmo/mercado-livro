package br.com.webmarmo.mercadolivro.repository

import br.com.webmarmo.mercadolivro.model.PurchaseModel
import org.springframework.data.repository.CrudRepository

interface PurchaseRepository : CrudRepository<PurchaseModel, Int> {

}
