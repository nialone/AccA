package mattecarra.accapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import mattecarra.accapp.models.ProfileEntity

@Database(entities = [ProfileEntity::class], version = 1)
abstract class AccaRoomDatabase : RoomDatabase() {

    abstract fun profileDao(): ProfileDao
    // abstract fun scheduleDao(): ScheduleDao

    companion object {
        @Volatile
        private var INSTANCE: AccaRoomDatabase? = null

        const val DATABASE_NAME = "acca_database"

        fun getDatabase(context: Context): AccaRoomDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                // Create database instance here
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AccaRoomDatabase::class.java,
                    DATABASE_NAME
                ).build()

                return INSTANCE as AccaRoomDatabase
            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}