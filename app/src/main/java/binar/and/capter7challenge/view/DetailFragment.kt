package binar.and.capter7challenge.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import binar.and.capter7challenge.R
import binar.and.capter7challenge.model.GetAllFilmItem
import binar.and.capter7challenge.view.data.FavoriteDatabase
import binar.and.capter7challenge.view.data.FavoriteFilm
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {
    lateinit var favorite : String
    var database : FavoriteDatabase? = null
    var filmfav : FavoriteFilm? = null

    lateinit var id : String
    lateinit var title : String
    lateinit var director : String
    lateinit var createdAt : String
    lateinit var synopsis : String
    lateinit var image: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_detail, container, false)
        val getfilm = arguments?.getParcelable<GetAllFilmItem>("detailfilm")
        val getfavfilm = arguments?.getParcelable<FavoriteFilm>("detailfilmfavorite")
        database = FavoriteDatabase.getInstance(requireActivity())

        if (getfilm != null){
            view.textjuduldetail.text = getfilm?.title
            view.textauthor.text = getfilm?.director
            view.textcreated.text = getfilm?.createdAt
            view.textdesc.text = getfilm?.synopsis
            Glide.with(requireContext()).load(getfilm?.image).into(view.gambar1)
            id = getfilm.id
            title = getfilm.title
            director = getfilm.director
            createdAt = getfilm.createdAt
            synopsis = getfilm.synopsis
            image = getfilm.image
        }

        if (getfavfilm != null){
            view.textjuduldetail.text = getfavfilm?.title
            view.textauthor.text = getfavfilm?.director
            view.textcreated.text = getfavfilm?.createdAt
            view.textdesc.text = getfavfilm?.synopsis
            Glide.with(requireContext()).load(getfavfilm?.image).into(view.gambar1)
            id = getfavfilm.id.toString()
            title = getfavfilm.title
            director = getfavfilm.director
            createdAt = getfavfilm.createdAt
            synopsis = getfavfilm.synopsis
            image = getfavfilm.image
        }

        favorite = "false"
        GlobalScope.launch {
            filmfav = database?.getFavorite()?.getFilmID(id.toInt())

            if (filmfav?.isfav == "true"){
                btnfavorite.setImageResource(R.drawable.ic_fav)
                favorite = "true"
            }

            if (filmfav?.isfav == "false"){
                btnfavorite.setImageResource(R.drawable.ic_fav)
                favorite = "false"
            }
        }

        view.btnfavorite.setOnClickListener {
            for (data in favorite){
                if (favorite == "true"){
                    btnfavorite.setImageResource(R.drawable.ic_fav)
                    favorite = "false"
                    GlobalScope.async {
                        database?.getFavorite()?.deleteFav(
                            FavoriteFilm(createdAt,
                                director,
                                id.toInt(),
                                image,
                                "",
                                synopsis,
                                title,
                                "false")
                        )
                    }
                    break
                }


                if (favorite == "false"){
                    btnfavorite.setImageResource(R.drawable.ic_fav)
                    favorite = "true"
                    GlobalScope.launch {
                        database?.getFavorite()?.addFilm(
                            FavoriteFilm(
                                createdAt,
                                director,
                                id.toInt(),
                                image,
                                "",
                                synopsis,
                                title,
                                "true"
                            )
                        )
                    }
                    break
                }
            }


        }
        return view
    }


}