package binar.and.capter7challenge.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import binar.and.capter7challenge.model.GetAllUserItem
import binar.and.capter7challenge.network.ApiClient
import retrofit2.Call
import retrofit2.Response

class ViewModelUser : ViewModel(){

    lateinit var liveDataUserItem : MutableLiveData<List<GetAllUserItem>>

    init {
        liveDataUserItem = MutableLiveData()
    }

    fun getLiveUserObserver() : MutableLiveData<List<GetAllUserItem>> {
        return liveDataUserItem
    }

    fun userApi(){
        ApiClient.instance.getAllUser()
            .enqueue(object : retrofit2.Callback<List<GetAllUserItem>>{
                override fun onResponse(
                    call: Call<List<GetAllUserItem>>,
                    getAllItem: Response<List<GetAllUserItem>>
                ) {
                    if (getAllItem.isSuccessful){
                        liveDataUserItem.postValue(getAllItem.body())

                    }else{
                        liveDataUserItem.postValue(null)

                    }
                }
                override fun onFailure(call: Call<List<GetAllUserItem>>, t: Throwable) {
                    liveDataUserItem.postValue(null)
                }
            })
    }

    fun updateDataUser(id : Int, username : String, completeName :String, dateofbirth : String, address : String){
        ApiClient.instance.updateUser(id, username, completeName, dateofbirth, address)
            .enqueue(object : retrofit2.Callback<List<GetAllUserItem>> {
                override fun onResponse(
                    call: Call<List<GetAllUserItem>>,
                    response: Response<List<GetAllUserItem>>
                ) {
                    liveDataUserItem.postValue(response.body())

                }
                override fun onFailure(call: Call<List<GetAllUserItem>>, t: Throwable) {
                    liveDataUserItem.postValue(null)
                }
            })

    }

}