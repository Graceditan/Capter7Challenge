package binar.and.capter7challenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import binar.and.capter7challenge.model.GetAllFilmItem
import binar.and.capter7challenge.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelFilm @Inject constructor(api : ApiService): ViewModel(){

    private var liveDataFilm = MutableLiveData<List<GetAllFilmItem>>()

    val film : LiveData<List<GetAllFilmItem>> = liveDataFilm

    init {
        viewModelScope.launch {
            val datafilm = api.getAllFilm()
            delay(2000)
            liveDataFilm.value = datafilm
        }
    }




}