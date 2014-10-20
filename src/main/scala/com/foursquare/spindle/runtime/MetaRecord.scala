// Copyright 2012 Foursquare Labs Inc. All Rights Reserved.

package com.foursquare.spindle

trait UntypedMetaRecord {
  def recordName: String
  def annotations: Annotations
  def createUntypedRawRecord: UntypedRecord
  def untypedFields: Seq[UntypedFieldDescriptor]
  def untypedIfInstanceFrom(x: AnyRef): Option[UntypedRecord]
}

trait MetaRecord[R <: Record[R]] extends UntypedMetaRecord {
  type Mutable <: R
  type Raw <: R

  def createRawRecord: Raw with MutableRecord[R]
  def fields: Seq[FieldDescriptor[_, R, this.type]]
  def ifInstanceFrom(x: AnyRef): Option[R]
}

object MetaRecord {
  def apply[T](implicit c: CompanionProvider[T]): c.CompanionT = c.provide
}
