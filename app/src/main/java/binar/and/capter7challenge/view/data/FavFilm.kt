package binar.and.capter7challenge.view.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavFilm {
    @Insert
    fun addFilm (favoriteFilm : FavoriteFilm) : Long
    @Delete()
    fun deleteFav(favoriteFilm: FavoriteFilm ):Int
    @Query("SELECT * FROM Favorite WHERE Favorite.id = :id")
    fun getFilmID(id:Int): FavoriteFilm
    @Query("SELECT *  FROM Favorite")
    fun getAllFav(): List<FavoriteFilm>
}