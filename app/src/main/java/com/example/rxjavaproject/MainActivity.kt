package com.example.rxjavaproject

import android.content.ContentValues.TAG
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rxjavaproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),MainAdapter.itemClickListner {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var adapter: MainAdapter
    private val retrofitService = RetrofitService.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(
            this,
            MainFactory(MainRepository(retrofitService))
        ).get(MainViewModel::class.java)

         adapter = MainAdapter(context = this,this)
        binding.recyclerview.adapter = adapter
        binding.btnShowMovies.setOnClickListener {
            viewModel.getMovie()
            binding.btnShowMovies.visibility = View.GONE
        }

        viewModel.movieList.observe(this, Observer {
            if (it != null) {
                Log.d("data ", "$it")
                adapter.setMovie(it)

            } else {
                Toast.makeText(this, "error in fetching data", Toast.LENGTH_LONG).show()
            }
        })
        /*       viewModel.errorMessage.observe(this, Observer {
            Log.d(TAG, "errorMessage: $it")
        })
    }

    override fun onDestroy() {
        //don't send events  once the activity is destroyed
        viewModel.disposable.dispose()
        super.onDestroy()
    }*/


    }
    fun openDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("DELETE ITEMVIEW")
        builder.setMessage("Do You want to delete the itemview??")
        builder.setPositiveButton("Yes",DialogInterface.OnClickListener{
                dialogInterface, i ->
            adapter.deltedata(i)

        })
        builder.setNegativeButton("No",DialogInterface.OnClickListener { dialogInterface, i ->

        })
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }



    override fun onclick(position: Int, data: Movie) {
//        openDialog()
        adapter.deltedata(position)
    }    }


