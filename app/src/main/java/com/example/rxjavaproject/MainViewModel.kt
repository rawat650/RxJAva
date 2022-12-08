package com.example.rxjavaproject

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(private val mainRepository: MainRepository) :ViewModel() {

    val movieList = MutableLiveData<List<Movie>>()
    val errorMessage = MutableLiveData<String>()
    lateinit var disposable: Disposable
    fun getMovie() {
        val response = mainRepository.getMovie()
        Log.d("response","$response")
        response.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getMoviesListObserver())
    }



    private fun getMoviesListObserver(): Observer<MovieList> {
        return object : Observer<MovieList>{
            override fun onSubscribe(d: Disposable) {
disposable=d            }

            override fun onNext(t: MovieList) {
                Log.d("Data","$t")
               movieList.postValue(t.mList)
            }

            override fun onError(e: Throwable) {
                errorMessage.postValue(e.message)
            }

            override fun onComplete() {

            }

        }
    }
}