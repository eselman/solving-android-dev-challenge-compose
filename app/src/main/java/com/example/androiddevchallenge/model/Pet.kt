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

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes

data class Pet(
    val animalType: AnimalType,
    val name: String?,
    val age: String?,
    val gender: Gender,
    @DrawableRes val imageResource: Int,
    var adopted: Boolean = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readSerializable() as AnimalType,
        parcel.readString(),
        parcel.readString(),
        parcel.readSerializable() as Gender,
        parcel.readInt(),
        parcel.readValue(Boolean::class.java.classLoader) as Boolean
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeSerializable(animalType)
        parcel.writeString(name)
        parcel.writeString(age)
        parcel.writeSerializable(gender)
        parcel.writeInt(imageResource)
        parcel.writeValue(adopted)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pet> {
        override fun createFromParcel(parcel: Parcel): Pet {
            return Pet(parcel)
        }

        override fun newArray(size: Int): Array<Pet?> {
            return arrayOfNulls(size)
        }
    }
}

enum class AnimalType {
    DOG,
    CAT
}

enum class Gender {
    BOY,
    GIRL
}
