# Pokedex Applicaion (Android)

This app is a clean architecture example

# Preview

<img src="/doc/preview.gif" align="center" width="35%"/>

### Light

<img src="/doc/light_mode.png"/>

### Dark

<img src="/doc/dark_mode.png"/>

<br><br>

# Tech stack

* Kotlin - First class and official programming language for Android development.
* Clean Architecture
* Jetpack Compose - Modern way to make Ui in android kotlin.
    * ViewModel - Stores UI-related data that isn't destroyed on UI changes.
    * Navigation
* Coroutines - For asynchronous and more.
  * Flow - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception
* Hilt, Dagger - Standard library to incorporate Dagger dependency injection into an Android application
* Room - A library that makes it easier to use SQLite databases
* Retrofit2 & OkHttp3 - A type-safe HTTP client for Android and Java & network logging
* Moshi - A modern JSON library for Kotlin and Java.
* Landscapist
    * Glide - Load images simply by using `GlideImage` composable function
    * Palette - Useful image plugins related to palette, such as extracting primary color sets
* Timber - This is a logger with a small, extensible API which provides utility on top of Android's normal Log class.

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
