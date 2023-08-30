# Pokedex Applicaion (Android)

This app is a clean architecture example

# Preview

### Light

<img src="/doc/light_mode.png"/>

### Dark

<img src="/doc/dark_mode.png"/>

<br><br>

# Tech stack

* Kotlin
* Clean Architecture
* Jetpack Compose
    * ViewModel
    * Navigation
* Coroutines
* Hilt
* Room
* Retrofit2 & OkHttp3
* Moshi
* Landscapist
    * Glide
    * Palette
* Timber

<br><br>

# Clean Architecture

This project is based on the clean architecture and MVVM pattern

<img src="/doc/clean_architecture_structure.png"/>

<br><br>

# Package Structure

```
pokedex
├─ di
├─ data
│  ├─ local
│  │  ├─ datasource
│  │  │  └─ interfaces
│  │  ├─ dao
│  │  └─ Databasse.kt
│  ├─ remote
│  │  ├─ datasource
│  │  │  └─ interfaces
│  │  ├─ model (dto)
│  │  ├─ response
│  │  └─ ApiService.kt
│  ├─ mapper
│  └─ repository (Impl)
├─ domain
│  ├─ entity
│  ├─ repository (interface)
│  ├─ usecase
│  └─ Result.kt
├─ ui
│  ├─ activity
│  ├─ screen 
│  └─ theme
│        ├─ Color.kt
│        ├─ Theme.kt
│        └─ Type.kt
└─ util
```

## Pokedex Open API

<img src="/doc/pokedex_api.png" width="320"/>

[PokeAPI](https://pokeapi.co/) is free and open to use. It is also very popular.<br>
PokeAPI provides a RESTful API interface to highly detailed objects built from thousands of lines of
data related to Pokémon.<br>

APIs used in this project

```
GET (Pokemon List)
https://pokeapi.co/api/v2/pokemon/?{limit=?&offset=?}"

GET (Pokemon Info)
https://pokeapi.co/api/v2/pokemon/{id or name}/
```

# License

```xml
Designed and developed by 2023 kose135 (JongGeun Lim)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
