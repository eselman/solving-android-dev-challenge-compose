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
package com.example.androiddevchallenge.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androiddevchallenge.model.Gender
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.model.defaultPets
import com.example.androiddevchallenge.ui.common.Toolbar

@Composable
fun PetDetailsView(navController: NavController) {
    val showConfirmationDialog = remember { mutableStateOf(false) }
    val pet = navController.previousBackStackEntry
        ?.arguments?.getParcelable<Pet>("pet")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .scrollable(state = rememberScrollState(0), orientation = Orientation.Vertical)
    ) {
        Toolbar(navController, remember { mutableStateOf(true) })
        Surface(
            shape = RoundedCornerShape(8.dp), elevation = 8.dp,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = pet?.imageResource!!),
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

        if (pet?.adopted == false) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = { showConfirmationDialog.value = true }) {
                    Text("Adopt Me")
                }
            }
        }

        if (pet?.adopted == true) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "${pet.name} is already adopted",
                    color = Color.Red,
                    textAlign = TextAlign.Center
                )
            }
        }

        if (showConfirmationDialog.value) {
            ShowConfirmationDialog(
                pet!!, navController,
                onDismiss = {
                    showConfirmationDialog.value = false
                }
            )
        }
    }
}

@Composable
fun ShowConfirmationDialog(pet: Pet, navController: NavController, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(text = "Confirm Adoption")
        },
        text = {
            Text("Are you sure you want to adopt ${pet.name}?. Remember this is a lifetime commitment")
        },
        confirmButton = {
            Button(
                onClick = {
                    val selectedPet = defaultPets.first { it.name == pet.name }
                    selectedPet.adopted = true
                    navController.popBackStack()
                }
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onDismiss()
                }
            ) {
                Text("CANCEL")
            }
        }
    )
}
