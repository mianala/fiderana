package com.mianala.fiderana

import android.app.Application
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController


class CategoryViewModel(application: Application): AndroidViewModel(application) {
    val database by lazy { AppDatabase.getDatabase(application) }
    val categoryDao = database.categoryDao()
    val categories = categoryDao.getAll()
}



@ExperimentalMaterialApi
@Composable
fun CategoryScreen(categoryViewModel: CategoryViewModel = viewModel(), navController: NavController) {
    val categories by categoryViewModel.categories.collectAsState(initial = emptyList())
    Column(Modifier.verticalScroll(rememberScrollState())) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp, 32.dp), Arrangement.spacedBy(16.dp)

        ) {
            categories.forEach {
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .background(Color.Cyan, shape = RoundedCornerShape(8.dp))
                        .height(IntrinsicSize.Min)
                        .fillMaxWidth()
                        .clickable { }
                ) {
                    Image(
                        modifier = Modifier
                            .width(224.dp)
                            .align(alignment = Alignment.TopEnd)
                            .offset(y = -68.dp, x = 50.dp),
                        painter = painterResource(id = R.drawable.ic_pray),
                        contentDescription = null,
                    )
                    Box(modifier = Modifier.wrapContentHeight()) {

                        Column(
                            Modifier
                                .padding(24.dp)
                                .padding(end = 100.dp),
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = it.title,
                                style = MaterialTheme.typography.h6,
                            )
                            Text(
                                text = "Hira 50",
                                style = MaterialTheme.typography.caption,
                                modifier = Modifier
                                    .padding(0.dp, 10.dp)
                            )
                            Text(
                                text = it.description,
                                style = MaterialTheme.typography.body2,
                            )

                        }
                    }

                }
            }
        }
    }
}