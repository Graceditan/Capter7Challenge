package binar.and.capter7challenge.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import binar.and.capter7challenge.R
import binar.and.capter7challenge.view.data.FavoriteFilm
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_favorite.view.*

class AdapterFavorite (var onclick : (FavoriteFilm)-> Unit) : RecyclerView.Adapter<AdapterFavorite.ViewHolder>() {
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view)
    var datafavorite : List<FavoriteFilm>? = null

    fun setDataFav(favorite  : List<FavoriteFilm>){
        this.datafavorite = favorite
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent,false)
        return ViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(datafavorite!![position].image).into(holder.itemView.gambarfav)

        holder.itemView.textjudulfav.text = datafavorite!![position].title
        holder.itemView.textdirecfav.text = datafavorite!![position].director
        holder.itemView.textreleasefav.text = datafavorite!![position].releaseDate

        holder.itemView.cardv.setOnClickListener {
            onclick(datafavorite!![position])
        }
    }

    override fun getItemCount(): Int {
        if (datafavorite == null){
            return 0
        }else{
            return datafavorite!!.size
        }
    }
}