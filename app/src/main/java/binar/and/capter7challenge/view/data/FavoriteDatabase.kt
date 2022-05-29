package binar.and.capter7challenge.view.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [FavoriteFilm::class],
    version = 4
)
abstract class FavoriteDatabase : RoomDatabase(){

    abstract fun getFavorite(): FavFilm

    companion object{
        private var getFavDB : FavoriteDatabase? = null

        fun getInstance(context: Context) : FavoriteDatabase?{
            if (getFavDB == null) {
                kotlin.synchronized(FavoriteDatabase::class) {
                    getFavDB = Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteDatabase::class.java,
                        "Favorite.db"
                    ).build()
                }
            }
            return getFavDB
        }

        fun destroy(){
            getFavDB = null
        }
    }
}