package com.example.projecto1.classes

//class Variables {

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
        println("El primer nombre es " + names[0])
        println("El ultimo nombre es " + names[names.size-1])

        println(add())
        println(product(10,92))

        printArray(names)
        println(names.joinToString())
        println(names.joinToString(separator = "/"))

        val numbers = arrayOf(1,2,3,4,5,6,7,8,9)

        isEven(numbers)

        println(getDay(6))
        println(getDay(8))

        val person = Person()
        val person2 = Person(name = "Sebas", age = 21)

        person.displayInformation()
        person2.displayInformation()

    }

    fun add():Int{
        val x = 5
        val y = 10

        return  x+y
    }

    fun product(x:Int, y:Int):Int{
        return x*y
    }

    fun printArray(names:Array<String>){
        for (name in names){
            println("Hello $name")
        }
    }

    fun isEven(numbers:Array<Int>){
        for (number in numbers){
            if (number%2==0){
                println("number $number is even")
            }else{
                println("number $number is odd")
            }
        }
    }

    fun getDay(day:Int):String{
        var name = ""

        when(day){
            1 -> name = "Monday"
            2 -> name = "Tuesday"
            3 -> name = "Wednesday"
            4 -> name = "Thursday"
            5 -> name = "Friday"
            6 -> name = "Saturday"
            7 -> name = "Sunday"
            else -> name = "Incorrect value"
        }

        return  name
    }

    class Person(val name:String = "default", val age:Int = 0){

        fun displayInformation(){
            println("El nombre es $name y la edad es $age")
        }
    }


//}