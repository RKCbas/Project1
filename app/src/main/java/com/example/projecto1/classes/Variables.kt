package com.example.projecto1.classes

class Variables {

    fun main(){

        //Numeric variables
        val age:Int = 20
        val longNumeric:Long = 1234567891011121314
        val temperature:Float = 27.123f
        val peso:Double = 60.4

        //String

        val gender:Char = 'M'
        val name:String = "Sebastián"

        //Boolean
        val isGreater:Boolean = false

        //Array
        val names = arrayOf("Erik", "Silvia", "Hector", "Gabriela")

        println("Welcome $name a tu primer programa de Kotlin")
        println("La edad es $age")
        println("La varialbe tipo long es $longNumeric")
        println("La temperatura es $temperature")
        println("El peso es $peso")
        println("El genero es $gender")
        println("Es mayor: $isGreater")
        println("El primer nombre es" + names[0])
        println("El ultimo nombre es" + names[names.size-1])

        println(add())

        println(product(10,92))

    }

    fun add():Int{
        val x = 5
        val y = 10

        return  x+y
    }

    fun product(x:Int, y:Int):Int{
        return x*y
    }

}