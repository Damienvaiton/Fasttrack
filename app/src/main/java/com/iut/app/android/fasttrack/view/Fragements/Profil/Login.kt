package com.iut.app.android.fasttrack.view.Fragements.Profil

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.iut.app.android.fasttrack.R
import com.iut.app.android.fasttrack.model.enums.FanErrors
import com.iut.app.android.fasttrack.viewModel.UserViewModel
import timber.log.Timber

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Login.newInstance] factory method to
 * create an instance of this fragment.
 */
class Login : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    val userVM by activityViewModels<UserViewModel>()


    //Database


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.connexion_page, container, false)
    }

    @SuppressLint("BinaryOperationInTimber")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Timber.tag("OnViewCreated").d("Passage dans account")


        val signupbtn = view.findViewById<Button>(R.id.signupbtn)

        val loginbtn = view.findViewById<Button>(R.id.submit)

        val usernameTV = view.findViewById<TextView>(R.id.username)
        val passwordTV = view.findViewById<TextView>(R.id.password)

        signupbtn.setOnClickListener {
            val fragment = Signup()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        loginbtn.setOnClickListener {


            userVM.Login(usernameTV.text.toString(), passwordTV.text.toString())

            userVM.loginResponseLD.observe(viewLifecycleOwner) {
                if (it.success()) {
                    val fragment = Account()
                    val transaction = requireActivity().supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.frame_layout, fragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                } else if (it.failed()) {
                    it.getFanError().forEach { error ->
                        when (error) {
                            FanErrors.VOID -> {
                                android.app.AlertDialog.Builder(context)
                                    .setTitle("Erreur")
                                    .setMessage("Veuillez remplir tous les champs")
                                    .setPositiveButton("OK", null)
                                    .show()
                            }

                            FanErrors.MAIL_NOT_FOUND -> {
                                android.app.AlertDialog.Builder(context)
                                    .setTitle("Erreur")
                                    .setMessage("Auncun compte n'est associé à cette adresse mail")
                                    .setPositiveButton("OK", null)
                                    .show()
                            }

                            FanErrors.PASSWORD_INCORRECT -> {
                                android.app.AlertDialog.Builder(context)
                                    .setTitle("Erreur")
                                    .setMessage("Mot de passe incorrect")
                                    .setPositiveButton("OK", null)
                                    .show()
                            }

                            else -> {
                                android.app.AlertDialog.Builder(context)
                                    .setTitle("Erreur")
                                    .setMessage("Une erreur est survenue")
                                    .setPositiveButton("OK", null)
                                    .show()
                            }
                        }
                    }
                }

            }


        }

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Account.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Login().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}