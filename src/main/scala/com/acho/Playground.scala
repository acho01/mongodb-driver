package com.acho

import com.acho.mongo.MongoClient

object Playground extends App {
  val client = new MongoClient
  val db = client.getDB("test")
  println("===========")

  db.collectionNames.foreach{ println }
}
