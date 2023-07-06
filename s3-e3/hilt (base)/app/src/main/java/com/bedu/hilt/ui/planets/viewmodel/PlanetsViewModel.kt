package com.bedu.hilt.ui.planets.viewmodel

import androidx.lifecycle.*
import com.bedu.hilt.data.model.People
import com.bedu.hilt.data.repository.MainRepository
import com.bedu.hilt.utils.NetworkHelper
import com.bedu.hilt.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetsViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _planets = MutableLiveData<Resource<List<People>>>()
    val planets: LiveData<Resource<List<People>>>
        get() = _planets

    init {
        fetchUsers()
    }

    fun onRefresh() {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _planets.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getPlanets().let {
                    if (it.isSuccessful) {
                        _planets.postValue(Resource.success(it.body()?.results))
                    } else _planets.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _planets.postValue(Resource.error("No internet connection", null))
        }
    }
}