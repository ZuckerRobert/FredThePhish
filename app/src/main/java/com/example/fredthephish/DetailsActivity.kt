package com.example.fredthephish

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DetailsActivity : AppCompatActivity() {
    companion object {
        const val TEXT_KEY = "text"
        val LOG_TAG = DetailsActivity::class.simpleName
    }
    //tag
    private val Any.TAG: String get() {
            val tag = javaClass.simpleName
            return if (tag.length <= 23) tag else tag.substring(0, 23)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        var cancelButton = findViewById<Button>(R.id.CancelButton)
        var createButton = findViewById<Button>(R.id.CreateButton)

        var textFieldName = findViewById<TextView>(R.id.TextFieldName)
        var editFieldName = findViewById<EditText>(R.id.EditFieldName)
        var textFieldDescription = findViewById<TextView>(R.id.TextFieldDescription)
        var editFieldDescription = findViewById<EditText>(R.id.EditFieldDescription)
        var textFieldAmount = findViewById<TextView>(R.id.TextFieldAmount)
        var editFieldAmount = findViewById<EditText>(R.id.EditFieldAmount)
        var textFieldReserved = findViewById<TextView>(R.id.TextFieldReserved)
        var editFieldReserved = findViewById<EditText>(R.id.EditFieldReserved)



        createButton.setOnClickListener {
            //irg wann bessere fehler
            if(editFieldAmount.text == null || editFieldDescription.text  == null || editFieldName.text  == null || editFieldReserved.text  == null)
            {
                Toast.makeText(createButton.context, "Pleas enter Data}", Toast.LENGTH_SHORT).show()

            }
            else{
                val fisch = DBEntries(
                    name = editFieldName.text.toString(),
                    description = editFieldDescription.text.toString(),
                    amount = editFieldAmount.text.toString(),
                    reserved = editFieldReserved.text.toString()
                )
                CreateNewFirebaseEntrie(fisch)
                finish()
            }
        }
    }

    private fun CreateNewFirebaseEntrie(fisch: DBEntries) {
        // Add a new document with a generated ID
        val db = Firebase.firestore
        db.collection("fisch").document("sammlung")
            .set(fisch)
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot added")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }

    }
}