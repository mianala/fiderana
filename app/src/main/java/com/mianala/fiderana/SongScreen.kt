package com.mianala.fiderana

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SongScreen() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), Arrangement.spacedBy(10.dp)
        ) {
            Text(text = "Title", style = MaterialTheme.typography.h3)
            Text(text = "HN5 - T130", style = MaterialTheme.typography.body1)
            Text(
                text = "Tsy moramora ny mankatoa " +
                        "\n Manefy ny sitrapo " +
                        "\n Manda ny tena koa", fontSize = 24.sp
            )
            Text(
                text = "Tsy moramora ny mankatoa " +
                        "\n Manefy ny sitrapo " +
                        "\n Manda ny tena koa", fontSize = 24.sp
            )
            Text(
                text = "Tsy moramora ny mankatoa " +
                        "\n Manefy ny sitrapo " +
                        "\n Manda ny tena koa", fontSize = 24.sp
            )
            Text(
                text = "Tsy moramora ny mankatoa " +
                        "\n Manefy ny sitrapo " +
                        "\n Manda ny tena koa", fontSize = 24.sp
            )
            Text(
                text = "Tsy moramora ny mankatoa " +
                        "\n Manefy ny sitrapo " +
                        "\n Manda ny tena koa", fontSize = 24.sp
            )
        }
    }
}