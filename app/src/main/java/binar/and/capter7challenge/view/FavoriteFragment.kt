package binar.and.capter7challenge.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import binar.and.capter7challenge.R
import binar.and.capter7challenge.view.adapter.AdapterFavorite
import binar.and.capter7challenge.view.data.FavoriteDatabase
import binar.and.capter7challenge.view.data.FavoriteFilm
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_favorite.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    lateinit var userManager: UserManager
    lateinit var adapterfav : AdapterFavorite
    var database : FavoriteDatabase? = null
    var film : FavoriteFilm? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_favorite, container, false)
        database = FavoriteDatabase.getInstance(requireContext())
        GlobalScope.async {
            film = database?.getFavorite()?.getFilmID(id.toInt())!!
            database?.getFavorite()?.getAllFav()
        }
        userManager = UserManager(requireContext())
        userManager.userUsername.asLiveData().observe(requireActivity()){
            view.tvfavusername.text = "Welcome $it"
        }
        view.home.setOnClickListener {
            view.findNavController().navigate(R.id.action_favoriteFragment_to_homeFragment)
        }
        view.profilefav.setOnClickListener {
            view.findNavController().navigate(R.id.action_favoriteFragment_to_profileFragment)
        }

        view.rcfav.layoutManager = LinearLayoutManager(requireContext())
        GlobalScope.launch {
            val listdata = database?.getFavorite()?.getAllFav()
            requireActivity().runOnUiThread {
                listdata.let {
                    if (listdata?.size == 0) {
                        checkdatafav.text = "Tambah film favorite mu"
                    }
                    adapterfav = AdapterFavorite(){
                        val bund = Bundle()
                        bund.putParcelable("detailfilmfavorite", it)
                        view.findNavController().navigate(R.id.action_favoriteFragment_to_detailFragment,bund)
                    }
                    view.rcfav.adapter = adapterfav
                    adapterfav.setDataFav(it!!)
                }
            }
        }
        return view
    }


}