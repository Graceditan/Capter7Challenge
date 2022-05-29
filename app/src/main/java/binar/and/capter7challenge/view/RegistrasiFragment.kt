package binar.and.capter7challenge.view

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import binar.and.capter7challenge.R
import binar.and.capter7challenge.model.GetAllUserItem
import binar.and.capter7challenge.model.ResponseRegister
import binar.and.capter7challenge.network.ApiClient
import binar.and.capter7challenge.viewmodel.ViewModelUser
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_registrasi.*
import kotlinx.android.synthetic.main.fragment_registrasi.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class RegistrasiFragment : Fragment() {

    lateinit var regisemailtext: String
    lateinit var dataUser: List<GetAllUserItem>
    lateinit var viewModel: ViewModelUser
    lateinit var password: String
    lateinit var toast: String
    lateinit var register: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_registrasi, container, false)

        getDataUserItem()

        view.btnregis.setOnClickListener {

            val username = regisusername.text.toString()
            regisemailtext = regisemail.text.toString()
            password = regispassword.text.toString()
            val confirmpass = confirmpassword.text.toString()


            if (regisusername.text.isNotEmpty() && regisemail.text.isNotEmpty()
                && regispassword.text.isNotEmpty()
                && confirmpassword.text.isNotEmpty()
            ) {

                if (password == confirmpass) {
                    for (i in dataUser.indices) {
                        if (regisemailtext == dataUser[i].email) {
                            register = "false"
                            break
                        } else {
                            register = "true"
                        }
                    }
                    if (register == "true") {

                        regisUser(username, regisemailtext, password)
                        view.findNavController()
                            .navigate(R.id.action_registrasiFragment_to_loginFragment)
                    } else {
                        toast = "Email Sudah Terdaftar"
                        customToast()
                    }


                } else {
                    toast = "Konfirmasi Password Tidak Sesuai"
                    customToast()
                }

            } else {
                toast = "Harap isi semua data"
                customToast()
            }

        }
        return view

    }

    fun customToast() {
        val text = toast
        val toast = Toast.makeText(
            requireActivity()?.getApplicationContext(),
            text,
            Toast.LENGTH_LONG
        )
        val text1 =
            toast.getView()?.findViewById(android.R.id.message) as TextView
        val toastView: View? = toast.getView()
        toastView?.setBackgroundColor(Color.TRANSPARENT)
        text1.setTextColor(Color.RED);
        text1.setTextSize(15F)
        toast.show()
        toast.setGravity(Gravity.CENTER or Gravity.TOP, 0, 1420)
    }

    fun regisUser(username: String, email: String, password: String) {
        ApiClient.instance.register(username, email, password)
            .enqueue(object : Callback<ResponseRegister> {
                override fun onResponse(
                    call: Call<ResponseRegister>,
                    response: Response<ResponseRegister>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(requireContext(), "register sukses", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        toast = "Email Sudah Terdaftar"
                        customToast()
                    }
                }

                override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {

                }
            })
    }

    fun getDataUserItem() {
        viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
        viewModel.getLiveUserObserver().observe(viewLifecycleOwner, Observer {
            dataUser = it

        })
        viewModel.userApi()

    }


}