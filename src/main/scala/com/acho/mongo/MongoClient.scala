package com.acho.mongo

import com.mongodb.MongoClientURI
import com.mongodb.{MongoClient => JavaMongoCLient}


class MongoClient(val user: String, val pwd: String,
                  val host: String, val port: Int) {
  private val instance = new JavaMongoCLient(new MongoClientURI((s"mongodb://$user:$pwd@$host:$port")))

  def this() = this("super", "Mario", "localhost", 27017)

  def createDB(name: String): DB = getDB(name)

  def getDB(name: String): DB = DB(instance.getDatabase(name))

  def dropDB(name: String): Unit = instance.dropDatabase(name)
}
