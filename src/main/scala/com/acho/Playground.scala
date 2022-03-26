package com.acho

import com.acho.mongo.MongoClient
import com.mongodb.client.model.Filters


object Playground extends App {
  val client = new MongoClient()
  val db = client.getDB("testdb")
  db.collectionNames.foreach(col => println(col))

  val col = db.readonlyCollection("books")
  println(col.name)
  println(col.fullName)
  println(col.getCount)
  val res = col.find(Filters.eq("title", "Clean Code"))
  println(res.first().toJson)

  val all = col.find().map{ _.toJson }.iterator()
  while (all.hasNext) {
    println(all.next())
  }

  val admin = db.adminableCollection("books")
  admin.drop()

  //  val update = db.updatableCollection("books")

//  val doc = new Document()
//  doc.put("title", "aaaaa")
//  doc.put("year", 2000)
//  val doc2 = new Document()
//  doc2.put("title", "Bible")
//  doc2.put("year", -2000)
//
//  update.+=(doc)
//  update.+=(doc2)
}
