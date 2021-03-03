/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.model

import com.example.androiddevchallenge.R

val defaultPets = listOf(
    Pet(AnimalType.DOG, "Felipe", "2 months", Gender.BOY, R.drawable.felipe),
    Pet(AnimalType.DOG, "Ellie", "2 months", Gender.GIRL, R.drawable.ellie),
    Pet(AnimalType.CAT, "Tiger", "1 month", Gender.BOY, R.drawable.tiger),
    Pet(AnimalType.CAT, "Tom", "1 month", Gender.BOY, R.drawable.tom)
)
