package com.iut.app.android.fasttrack.view.Fragements.Profil

import android.app.AlertDialog
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


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Signup : Fragment() {

    private var param1: String? = null
    private var param2: String? = null


    val userVM by activityViewModels<UserViewModel>()


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

        return inflater.inflate(R.layout.inscription_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //Find elements
        val firstname = view.findViewById<TextView>(R.id.firstname)
        val lastname = view.findViewById<TextView>(R.id.lastname)
        val usernameTV = view.findViewById<TextView>(R.id.username)
        val passwordTV = view.findViewById<TextView>(R.id.password)
        val buttonEnregistrer = view.findViewById<Button>(R.id.submit)


        buttonEnregistrer.setOnClickListener {

            userVM.Signup(
                firstname.text.toString(),
                "Damien",
                usernameTV.text.toString(),
                passwordTV.text.toString(),
                null,
                null,
                null
            )


        }
        userVM.signupResponseLD.observe(viewLifecycleOwner) {
            if (it.success()) {
                val fragment = Login()
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frame_layout, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            } else if (it.failed()) {
                it.getFanError().forEach { error ->
                    when (error) {
                        FanErrors.VOID -> {
                            AlertDialog.Builder(context)
                                .setTitle("Erreur de saisie")
                                .setMessage("Veuillez remplir tous les champs")
                                .setPositiveButton("OK", null)
                                .show()
                        }

                        FanErrors.MAIL_ALREADY_EXISTS -> {
                            AlertDialog.Builder(context)
                                .setTitle("Erreur d'adresse mail")
                                .setMessage("L'adresse mail est déjà utilisée")
                                .setPositiveButton("OK", null)
                                .show()
                        }

                        else -> {
                            AlertDialog.Builder(context)
                                .setTitle("Erreur")
                                .setMessage("Une erreur est survenue")
                                .setPositiveButton("OK", null)
                                .show()}
                    }
                }
            }
        }
    }

}



