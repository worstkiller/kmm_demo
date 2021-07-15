# Kotlin Multiplatform Mobile Demo App - Kmm Demo App
 This project is a demonstration of KMM SDK provided by the [JetBrains](https://www.jetbrains.com/) for sharing the business or common code between the differnt platforms in our case it is iOS and Android platforms. 
You only need to write Native code where needed rest goes in your shared code as a library which can be integrated as a library or framework. 

This project shows the sharing of the common API code for both the platforms with a single [Ktor Client](https://ktor.io/docs/http-client-multiplatform.html) making the API's calls. iOS and Android clients calls these api to show a list of [Dog](https://api.thedogapi.com/) on frontend using [Jetpack Compose](https://developer.android.com/jetpack/compose) and [SwiftUI](https://developer.apple.com/xcode/swiftui/) as the Native UI.
KMM gives us the flexibility in choosing your UI and you can start integrating KMM in any phase of your project.

## 📱Screenshots
Android | iOS
------------ | -------------
<img src="https://github.com/worstkiller/kmm_demo/blob/master/screenshots/Screenshot_20210716_014335_com.vikas.kotlinmultiplatformdemo.android.jpg" height="649" width="300">|<img src="https://github.com/worstkiller/kmm_demo/blob/master/screenshots/Screenshot_ios_smple.png" height="649" width="350">

## 🖥️ Tech
- [KMM](https://kotlinlang.org/docs/mobile/getting-started.html) - Kotlin Multiplatform Mobile 
- [SwiftUI](https://developer.apple.com/xcode/swiftui/) - iOS Native UI
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Android Native UI
- [Ktor Client](https://ktor.io/docs/http-client-multiplatform.html) - Networking Client


## 👩‍💻 How to install and Requirements
- Download the Project
- Open in Android Studio Arctic Fox Beta 5 or above for working with shared code & android project.
- Install the required Plugin of KMM in Android Studio.
- Xcode 12.3 iOs project for editing SwiftUI Native code
- Run in iOS or Android emulator or Device from Android Studio with KMM plugin


## ✔️ Code Sample Sections
Common Code Api 
```kotlin
  fun getDoggoList(count: Int): Flow<BaseResponse<List<DoggoResponseModel>>> {
        return flow<BaseResponse<List<DoggoResponseModel>>> {
            //do repository level operation before consuming the data in the viewmodel
            val doggoList = doggoApiServiceHandler.getDoggoList(count = count)
            if (doggoList.isNullOrEmpty()) {
                emit(BaseResponse.error(data = listOf(), msg = "No data found"))
            } else {
                emit(BaseResponse.success(data = doggoList))
            }
        }.catch { ex ->
            emit(DoggoApiErrorHandler.processError(ex))
        }.onStart { emit(BaseResponse(BaseViewState.LOADING)) }
    }
```

iOS Side Api call (KmmDemoViewModel)
```swift
    func getDoggoList(){
        doggoListRepository.getDoggoList(count: 15){ data in
            DispatchQueue.main.async {
                switch data.status {
                case is BaseViewState.ERROR :
                    //do something here on error
                    self.apiStatus = data.status
                case is BaseViewState.LOADING :
                    //do something here on loading
                    self.apiStatus = data.status
                case is BaseViewState.SUCCESS :
                    //process data received
                    self.apiStatus = data.status
                    self.doggoList = data.data as? [DoggoResponseModel] ?? []
                default:
                    self.apiStatus = data.status
                }
            }
        }
    }
```
Android Side Api Call (KmmDemoViewModel)
```kotlin
    private fun getDoggoList() {
        viewModelScope.launch {
            doggoListRepository.getDoggoList(15).collect {
                when (it.status) {
                    is BaseViewState.ERROR -> {
                        //do something here on error
                        _apiStatus.value = it.status
                    }
                    is BaseViewState.LOADING -> {
                        //do something here on loading
                        _apiStatus.value = it.status
                    }
                    is BaseViewState.SUCCESS -> {
                        //process data received
                        _doggoList.value = it.data
                        _apiStatus.value = it.status
                    }
                }
            }
        }
    }
```


## 💁 Feel Free to Reach for :
 * Suggestions
 * Code Improvements
 * Issues


## 🤝 Let's get Social
 * [Twitter](https://twitter.com/vikaskum09)
 * [LinkedIn](https://www.linkedin.com/in/vikaskumar09/)
 * [Behance](https://www.behance.net/vikaskum)
 * [Portfolio](https://vikas.dev)
 * [Email](mailto:contactvikasrajput@gmail.com)


## 🙏 Credits
- [Ktor](https://ktor.io/docs/http-client-multiplatform.html)
- [IntelliJ Youtube Channel](https://www.youtube.com/watch?v=GcqFhoUuNNI&ab_channel=KotlinbyJetBrains)
- [KMM Docs](https://kotlinlang.org/docs/mobile/home.html)

