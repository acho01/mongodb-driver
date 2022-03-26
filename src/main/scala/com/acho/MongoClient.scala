package com.acho

import com.mongodb.Mongo

class MongoClient(val host: String, val port: Int) {
  require(host != null, "Please provide hostname.")
  private val instance = new Mongo(host, port)
  def this() = this("localhost", 27017)

  def version(): String = instance.getVersion

  def dropDB(name: String): Unit = instance.dropDatabase(name)

  def getDB(name: String): DB = DB(instance.getDB(name))

  def createDB(name: String): DB = getDB(name)
}
