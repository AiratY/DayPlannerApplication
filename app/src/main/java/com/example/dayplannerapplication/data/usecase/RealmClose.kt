package com.example.dayplannerapplication.data.usecase

import io.realm.Realm

class RealmClose {
    fun execute(realm: Realm) {
        realm.close()
    }
}
