package com.example.dayplannerapplication.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.sql.Timestamp
import java.util.*

open class Task()  : RealmObject() {
    @PrimaryKey
    var id: Int = 0
    var dateStart: Long = Date().time
    var dateEnd: Long = Date().time
    var name: String = "name"
    var description: String = " "
    constructor(
        id: Int,
        dateStart:Long,
        dateEnd: Long,
        name: String,
        description: String
    ) : this() {
        this.id = id
        this.dateStart = dateStart
        this.dateEnd = dateEnd
        this.name = name
        this.description = description
    }
}
