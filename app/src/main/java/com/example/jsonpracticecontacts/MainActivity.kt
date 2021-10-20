package com.example.jsonpracticecontacts

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
     var contacts = ArrayList<UserDetails.Datum>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etname = findViewById<View>(R.id.editText) as EditText
        val showbtn = findViewById<View>(R.id.button) as Button
        val textView = findViewById<View>(R.id.text) as TextView

        doGetListResources()

        showbtn.setOnClickListener {
            val index = etname.text.toString().toInt()
            //can use this
            //textView.text = contacts.elementAt(index).name
            //OR
            textView.text = contacts.get(index).name
        }
    }

    private fun doGetListResources() {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please wait")
        progressDialog.show()
        val call: Call<List<UserDetails.Datum>> = apiInterface!!.doGetListResources()

        call?.enqueue(object : Callback<List<UserDetails.Datum>> {
            override fun onResponse(
                call: Call<List<UserDetails.Datum>>,
                response: Response<List<UserDetails.Datum>>
            ) {
                Log.d("TAG", response.code().toString() + "")
                val resource: List<UserDetails.Datum>? = response.body()
                val datumList = resource
                progressDialog.dismiss()

                for (datum in datumList!!) {
                    contacts.add(datum)
                }
            }

            override fun onFailure(call: Call<List<UserDetails.Datum>>, t: Throwable?) {
                call.cancel()
                progressDialog.dismiss()
            }
        })

    }
}
