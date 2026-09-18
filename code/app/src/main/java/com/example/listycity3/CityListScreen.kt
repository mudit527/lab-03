package com.example.listycity3

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listycity3.ui.theme.ListyCity3Theme

@Composable
fun CityListScreen(
    cities: List<City>,
    onUpdateCity: (City, City) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedCity by remember { mutableStateOf<City?>(null) }
    var cityName by remember { mutableStateOf("") }
    var province by remember { mutableStateOf("") }

    Column(modifier = modifier) {

        if (selectedCity != null) {

            OutlinedTextField(
                value = cityName,
                onValueChange = { cityName = it },
                label = { Text("City") }
            )

            OutlinedTextField(
                value = province,
                onValueChange = { province = it },
                label = { Text("Province") }
            )

            Button(
                onClick = {
                    val updatedCity = City(
                        cityName,
                        province
                    )

                    onUpdateCity(
                        selectedCity!!,
                        updatedCity
                    )

                    selectedCity = null
                }
            ) {
                Text("UPDATE CITY")
            }
        }

        LazyColumn {
            itemsIndexed(cities) { index, city ->

                CityRow(
                    city = city,
                    onClick = {
                        selectedCity = city
                        cityName = city.name
                        province = city.province
                    }
                )

                if (index < cities.lastIndex) {
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
fun CityRow(
    city: City,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Text(
            text = city.name,
            fontSize = 30.sp,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = city.province,
            fontSize = 30.sp,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CityListScreenPreview() {
    ListyCity3Theme {
        CityListScreen(
            cities = listOf(
                City("Edmonton", "AB"),
                City("Vancouver", "BC"),
                City("Toronto", "ON")
            ),
            onUpdateCity = { _, _ -> }
        )
    }
}