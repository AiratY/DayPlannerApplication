package com.example.dayplannerapplication.data.usecase

import android.content.Context
import com.example.dayplannerapplication.R
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmInit {
    fun execute(context: Context) {
        Realm.init(context)

        val config = RealmConfiguration
            .Builder()
            .name(R.string.realm_name.toString())
            .allowQueriesOnUiThread(true)
            .allowWritesOnUiThread(true)
            .build()
        Realm.setDefaultConfiguration(config)
    }
}
