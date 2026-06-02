package dam_A51706.finalproject.data.repository

import android.app.Application
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthRepo ( val application: Application) {

    val firebaseUserMutableLiveData: MutableLiveData<FirebaseUser> = MutableLiveData()
    val userLoggedOutMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    init {
        if(auth.currentUser != null){
            firebaseUserMutableLiveData.postValue(auth.currentUser)
        }
    }

    fun register (email: String, pass: String) {
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener{
            @Override
             fun onComplete(task: Task<AuthResult?>) {
                if (task.isSuccessful) {
                    firebaseUserMutableLiveData.postValue(auth.currentUser)

                    Toast.makeText(application, "Account was created successfully! Check email to verify.", Toast.LENGTH_SHORT ).show()

                    //auth.currentUser!!.sendEmailVerification()
                    //auth.signOut()
                } else {
                    Toast.makeText(application, task.exception?.message, Toast.LENGTH_SHORT ).show()
                }
            }

        }
    }

    fun login (email: String, pass: String) {
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
            @Override
            fun onComplete(task: Task<AuthResult?>) {
                if (task.isSuccessful) {
                    firebaseUserMutableLiveData.postValue(auth.currentUser)
                } else {
                    Toast.makeText(application, task.exception?.message, Toast.LENGTH_SHORT ).show()
                }
            }
        }
    }

    fun signOut () {
        auth.signOut()
        userLoggedOutMutableLiveData.postValue(true)
    }

}