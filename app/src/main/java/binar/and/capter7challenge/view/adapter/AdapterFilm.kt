package binar.and.capter7challenge.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import binar.and.capter7challenge.R
import binar.and.capter7challenge.model.GetAllFilmItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_film.view.*


class AdapterFilm (private val onclick : (GetAllFilmItem)->Unit) : RecyclerView.Adapter<AdapterFilm.ViewHolder>() {
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view)
    var datafilm : List<GetAllFilmItem>? = null

    fun setDataFilm(film: List<GetAllFilmItem>){
        this.datafilm = film
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterFilm.ViewHolder {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent,false)
        return ViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: AdapterFilm.ViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(datafilm!![position].image).into(holder.itemView.gambar)

        holder.itemView.textjudul.text = datafilm!![position].title
        holder.itemView.textdirec.text = datafilm!![position].director
        holder.itemView.textrelease.text = datafilm!![position].releaseDate

        holder.itemView.card.setOnClickListener {
            onclick(datafilm!![position])
        }
    }

    override fun getItemCount(): Int {
        if (datafilm == null){
            return 0
        }else{
            return datafilm!!.size
        }
    }

}