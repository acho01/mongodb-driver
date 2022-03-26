package com.acho.mongo

import com.mongodb.client.{MongoDatabase => MongoDB}

import scala.jdk.javaapi.CollectionConverters.asScala

class DB private(val instance: MongoDB) {
  def collectionNames: List[String] = asScala(instance.listCollectionNames()).to(List)
}

object DB {
  def apply(instance: MongoDB) = new DB(instance)
}
