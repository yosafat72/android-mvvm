package labs.yohesu.mvvm2.networks

sealed class ResponseHelper <out R>{

    data class Success<out T>(val body: T) : ResponseHelper<T>()
    data class Error(val message: String?) : ResponseHelper<Nothing>()
    object Loading: ResponseHelper<Nothing>()

}