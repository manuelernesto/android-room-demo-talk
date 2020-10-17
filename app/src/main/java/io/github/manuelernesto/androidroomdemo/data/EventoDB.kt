package io.github.manuelernesto.androidroomdemo.data

//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//
//@Database(entities = [], version = 1)
//abstract class EventoDB : RoomDatabase() {
//
//
//    companion object {
//        @Volatile
//        private var TALK_INSTANCE: EventoDB? = null
//
//        fun getDatabase(context: Context): EventoDB {
//            val tempInstance = TALK_INSTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    EventoDB::class.java,
//                    "db_evento"
//                ).build()
//                TALK_INSTANCE = instance
//                return instance
//            }
//        }
//    }
//
//}