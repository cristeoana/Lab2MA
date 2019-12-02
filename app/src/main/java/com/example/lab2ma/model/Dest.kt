package com.example.lab2ma.model

class Dest {
    var id: Int=0
    var country: String? = null
    var city: String? = null
    var price: Int? =0
    var description: String? = null

    constructor(){}

    constructor(id: Int, country: String, city: String, price: Int, description:String)
    {
        this.id = id
        this.country = country
        this.city = city
        this.price = price
        this.description = description
    }
}