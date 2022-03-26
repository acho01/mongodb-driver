package com.acho.mongo

import com.mongodb.client.{FindIterable, MongoCollection => JavaMongoCollection}
import org.bson.Document
import org.bson.conversions.Bson

import scala.language.{existentials, postfixOps}

class MongoCollection(override val instance: JavaMongoCollection[Document]) extends Readonly

trait Readonly {
  protected val instance: JavaMongoCollection[Document]

  def name: String = instance.getNamespace.getCollectionName

  def fullName: String = instance.getNamespace.getFullName

  def find(doc: Bson): FindIterable[Document] = instance find doc

  def find(): FindIterable[Document] = instance find

  def getCount: Long = instance.countDocuments()
}

trait Adminable extends Readonly {
  def drop(): Unit = instance.drop()

  def dropIndices(): Unit = instance.dropIndexes()
}

trait Updatable extends Readonly {
  def -=(doc: Document): Unit = instance deleteOne doc

  def +=(doc: Document): Unit = instance insertOne doc
}
