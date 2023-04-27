package ni.edu.uca.projectsqlite.database

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class CityVM(private val repository: CityRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allWords: LiveData<List<City>> = repository.allCities.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(city: City) = viewModelScope.launch {
        repository.insert(city)
    }
}

class CityVMFactory(private val repository: CityRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityVM::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CityVM(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}