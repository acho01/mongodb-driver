package com.acho.mongo

import com.mongodb.client.{MongoCollection => JavaMongoCollection, MongoDatabase => JavaMongoDB}
import org.bson.Document

import scala.jdk.javaapi.CollectionConverters.asScala

class DB private(val instance: JavaMongoDB) {
  def collectionNames: List[String] = asScala(instance.listCollectionNames()).to(List)

  private def collection(name: String): JavaMongoCollection[Document] = instance.getCollection(name)

  def readonlyCollection(name: String) = new MongoCollection(collection(name))

  def adminableCollection(name: String) = new MongoCollection(collection(name)) with Adminable

  def updatableCollection(name: String) = new MongoCollection(collection(name)) with Updatable

  def superCollection(name: String) = new MongoCollection(collection(name)) with Adminable with Updatable
}

object DB {
  def apply(instance: JavaMongoDB) = new DB(instance)
}
