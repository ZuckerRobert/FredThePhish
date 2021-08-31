package com.example.fredthephish

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase




class MainActivity : AppCompatActivity() {
    private val db = Firebase.firestore

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    var listOfItems : MutableList<DBEntries> = mutableListOf()

    private var sammlung = "fisch"
    //tag
    private val Any.TAG: String
        get() {
            val tag = javaClass.simpleName
            return if (tag.length <= 23) tag else tag.substring(0, 23)
        }

    // Create a new user with a first and last name
    //private val sammlung = Fisch("Barsch", "Essen", 23)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //navigate to create new entrie
        var newButton = findViewById<Button>(R.id.NewButton)
        newButton.setOnClickListener{
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.TEXT_KEY, "Text from first Activity!")
            startActivity(intent)
        }


        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        //wait for this
        var tempi =  getDBEntries(sammlung)

        //liste von fischen mitgeben TODO
        adapter = RecyclerAdapter(listOfItems)
        recyclerView.adapter = adapter


        // Create a new item
        val user = hashMapOf(
            "first" to "Ada",
            "last" to "Lovelace",
            "born" to 1815
        )
        var fisch = DBEntries(
            name = "Barsch geräuchert",
            amount = "12",
            description = "geräuchert",
            reserved = "5",
        )

        /*
        // Add a new document with a generated ID
        db.collection("fisch").document("sammlung")
            .set(fisch)
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot added")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }

        */
    }


    private fun getDBEntries(sammlung: String) {
        //get collection
        db.collection(sammlung)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    var remp = document.data["firstName"]
                    Log.d(TAG, "remp =  ${remp}")

                    var fisch = DBEntries(
                        name = document.data["name"].toString(),
                        amount = document.data["amount"].toString(),
                        description = document.data["description"].toString(),
                        reserved = document.data["reserved"].toString(),
                    )

                    listOfItems.add(fisch)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }

}






