package com.krkhvr.gamemvvmca.presentation.screens.updatepost.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.krkhvr.gamemvvmca.R
import com.krkhvr.gamemvvmca.presentation.components.DefaultEdiText
import com.krkhvr.gamemvvmca.presentation.components.DialogCapturePicture
import com.krkhvr.gamemvvmca.presentation.screens.updatepost.UpdatePostViewModel
import com.krkhvr.gamemvvmca.presentation.ui.theme.GameMVVMCATheme
import com.krkhvr.gamemvvmca.presentation.ui.theme.Red500

@Composable
fun UpdatePostContent(
    paddingValues: PaddingValues,
    viewModel: UpdatePostViewModel = hiltViewModel()
) {

    viewModel.resultingActivityHandler.Handle()
    val dialogState = remember { mutableStateOf(false) }
    DialogCapturePicture(
        status = dialogState,
        takePhoto = {
            viewModel.takePhoto()
        },
        pickImage = {
            viewModel.pickImage()
        }
    )

    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth(),
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .height(170.dp)
                    .background(Red500)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if(viewModel.state.image != ""){
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(170.dp)
                                .clickable {
                                    dialogState.value = true
                                },
                            contentScale = ContentScale.Crop,
                            model = viewModel.state.image,
                            contentDescription = ""
                        )
                    }else{
                        Image(
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .height(120.dp)
                                .clickable {
                                    dialogState.value = true
                                },
                            painter = painterResource(id = R.drawable.add_image),
                            contentDescription = ""
                        )

                        Text(
                            text = "SELECCIONA UNA IMAGEN",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            DefaultEdiText(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                value = viewModel.state.name,
                label = "Nombre del juego",
                icon = Icons.Default.Face,
                onValueChange = {
                    viewModel.onNameInput(it)
                },
                errorMsg = ""
            )

            DefaultEdiText(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                value = viewModel.state.description,
                label = "Descripción",
                icon = Icons.AutoMirrored.Filled.List,
                onValueChange = {
                    viewModel.onDescriptionInput(it)
                },
                errorMsg = ""
            )

            Text(
                modifier = Modifier.padding(vertical = 15.dp),
                text = "CATEGORIAS",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )

            viewModel.radioOptions.forEach {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .fillMaxWidth()
                        .selectable(
                            selected = (it.category == viewModel.state.category),
                            onClick = {
                                viewModel.onCategoryInput(it.category)
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (it.category == viewModel.state.category),
                        onClick = {
                            viewModel.onCategoryInput(it.category)
                        }
                    )

                    Text(
                        modifier = Modifier.width(100.dp),
                        text = it.category
                    )

                    Image(
                        modifier = Modifier.height(30.dp),
                        painter = painterResource(id = it.image),
                        contentDescription = ""
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewNewPostScreen() {
    GameMVVMCATheme(darkTheme = true) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            UpdatePostContent(PaddingValues())
        }
    }
}