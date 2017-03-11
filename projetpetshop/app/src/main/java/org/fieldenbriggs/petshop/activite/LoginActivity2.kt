package org.fieldenbriggs.petshop.activite

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import org.fieldenbriggs.petshop.R
import org.fieldenbriggs.petshop.service.AnimalerieService
import org.fieldenbriggs.request.UtilisateurLogRequest
import org.fieldenbriggs.response.UtilisateurLogResponse
import retrofit2.Call
import retrofit2.Response

class LoginActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        // Identification des elements de l'interface
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
        val courriel : EditText = findViewById(R.id.inUser) as EditText
        val motDePasse : EditText = findViewById(R.id.inPass) as EditText
        val btnLogin : Button = findViewById(R.id.btnLog) as Button
        val btnCompte : Button = findViewById(R.id.btnNew) as Button
        val chargement : TextView = findViewById(R.id.prgChargement) as TextView
        val progress : ProgressBar = findViewById(R.id.progress) as ProgressBar
        var isLoading : Boolean = false
        // Function used to login
        fun login(){
            val ur = UtilisateurLogRequest(courriel.text.toString(), motDePasse.text.toString())
            AnimalerieService.getInstance().server.signin(ur).enqueue(object : retrofit2.Callback<UtilisateurLogResponse> {
                override fun onResponse(call: Call<UtilisateurLogResponse>, response: Response<UtilisateurLogResponse>) {

                    if (response.isSuccessful) {
                        runOnUiThread {
                            progress.visibility = View.INVISIBLE
                            chargement.visibility = View.INVISIBLE
                        }
                        // Si la requête marche on met l'utilisateur courant et on rentre dans l'application
                        AnimalerieService.getInstance().utilisateurCourant = response.body()
                        isLoading = false
                        val intentLoging = Intent(applicationContext, MainActivity::class.java)
                        intentLoging.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        intentLoging.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(intentLoging)
                    } else {
                        runOnUiThread {
                            progress.visibility = View.INVISIBLE
                            chargement.visibility = View.INVISIBLE
                        }
                        isLoading = false
                        Toast.makeText(applicationContext, "Authentification Echouée! : Authentifiant ou mot de passe invalide!", Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(call: Call<UtilisateurLogResponse>, t: Throwable) {
                    runOnUiThread {
                        progress.visibility = View.INVISIBLE
                        chargement.visibility = View.INVISIBLE
                    }
                    t.printStackTrace()
                    isLoading = false
                    Toast.makeText(applicationContext, "Aucune connexion au serveur!", Toast.LENGTH_SHORT).show()
                }
            })
        }

        // Click listeners
        btnLogin.setOnClickListener {
            runOnUiThread {
                progress.visibility = View.VISIBLE
                chargement.visibility = View.VISIBLE
            }
            if (!isLoading) {
                isLoading = true
                login()
            }
        }
        // Listener de creation de compte
        btnCompte.setOnClickListener {
            val intentLog = Intent(applicationContext, AjouterUtilisateurActivity::class.java)
            startActivity(intentLog) }
    }

}
