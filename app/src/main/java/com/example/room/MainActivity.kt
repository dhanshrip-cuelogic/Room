package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room

class MainActivity : AppCompatActivity() {

    lateinit var db : AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(this,AppDatabase::class.java,"mydb").build()

        val user1 = User(1,"User1", 30)
        val user2 = User(2,"User2",35)

        val thread = Thread{
            with(db.userDao()){
                println("Going to insert data")
                this.insertUser(user1)
                this.insertUser(user2)
            }

            db.userDao().getUser().forEach() {
                println("-----------------------------------------")
                println(it.id)
                println(it.name)
                println(it.age)

            }

            println("Going to insert more data")
            val user3 = User(3, "User3", 28)
            db.userDao().insertUser(user3)

            db.userDao().getUser().forEach() {
                println("-----------------------------------------")
                println(it.id)
                println(it.name)
                println(it.age)

            }

            println("Going to update data")
            val updatedUser3 = User(3, "User3", 25)
            db.userDao().updateUser(updatedUser3)

            db.userDao().getUser().forEach() {
                println("-----------------------------------------")
                println(it.id)
                println(it.name)
                println(it.age)

            }

            println("Going to delete updated user 3 i.e., User(3, \"User3\", 25)")
            db.userDao().deleteUser(updatedUser3)
            db.userDao().getUser().forEach() {
                println("-----------------------------------------")
                println(it.id)
                println(it.name)
                println(it.age)

            }

        }

        thread.start()


    }
}