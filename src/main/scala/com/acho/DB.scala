package com.acho

import com.mongodb.{DB => MongoDB}

class DB private(val instance: MongoDB){
}

object DB {
  def apply(instance: MongoDB) = new DB(instance)
}
