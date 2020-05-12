# COVID-19

### Android
This repository contains simple COVID19 data monitoring by country. Develop in android over MVVM, Live Data, Koin, RxBinding, etc.

### Preview 🎉
<img src="https://user-images.githubusercontent.com/20619434/78194762-09dc7200-744c-11ea-8d1f-754e71bdfbc2.png"/>

<a href="https://github.com/AnelCC/COVID-19/raw/42c474e91304fb9b8e224bdd8abfa3d83225ba0e/apk/covidTrack.apk"><img src="https://locations.massageenvy.com/images/google-play-badge.png" width="200"/></a>

### Library References
1. Koin
2. Retrofit
3. RxBinding
4. RxBinding
5. Picasso
6. Gson
7. OkHttp3
8. <a href="https://corona.lmao.ninja/">API</a>


### Package Structure
```
com.anelcc.coronavirustrack    # Root Package
.
├── api                 # For API Service.
├── model               # Model classes
├── repository          # Repository to handle network API.
│
├── ui                  # Activity/View layer
│   │── country         # Screen Activity and ViewModel
│   └── countrydetail   # Screen Activity and ViewModel
│
└── utils
```


### License

```
Copyright (c) 2020 Anel Calvo

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```