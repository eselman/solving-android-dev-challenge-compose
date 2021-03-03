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
package com.example.androiddevchallenge.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.model.Gender
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.model.defaultPets

@Composable
fun PetCard(pet: Pet, navController: NavController) {
    Surface(
        shape = RoundedCornerShape(8.dp), elevation = 8.dp,
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                navController.currentBackStackEntry
                    ?.arguments?.putParcelable("pet", pet)
                navController.navigate("petDetails")
            }
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = pet.imageResource),
                contentDescription = "This is a beautiful pet for adoption",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )

            Column(modifier = Modifier.padding(16.dp)) {
                val petGenderTitle = when (pet.gender) {
                    Gender.BOY -> "Male"
                    Gender.GIRL -> "Female"
                }

                if (pet.adopted) {
                    Text("ADOPTED", color = Color.Red, style = MaterialTheme.typography.h2)
                }

                Text(pet.name!!, style = MaterialTheme.typography.h4)
                Row {
                    Text(
                        "Gender: ", style = MaterialTheme.typography.h5,
                        modifier = Modifier.alignByBaseline()
                    )
                    Text(petGenderTitle, modifier = Modifier.alignByBaseline())
                }

                Row {
                    Text(
                        "Age: ",
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.alignByBaseline()
                    )
                    Text(pet.age!!, modifier = Modifier.alignByBaseline())
                }
            }
        }
    }
}

@Composable
@Preview
fun DefaultPetCard() {
    PetCard(defaultPets[0], navController = rememberNavController())
}
