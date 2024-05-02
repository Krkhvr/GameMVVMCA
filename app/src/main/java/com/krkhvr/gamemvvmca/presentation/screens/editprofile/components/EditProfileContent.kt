package com.krkhvr.gamemvvmca.presentation.screens.editprofile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.krkhvr.gamemvvmca.R
import com.krkhvr.gamemvvmca.presentation.components.DefaultButton
import com.krkhvr.gamemvvmca.presentation.components.DefaultEdiText
import com.krkhvr.gamemvvmca.presentation.components.DialogCapturePicture
import com.krkhvr.gamemvvmca.presentation.screens.editprofile.EditProfileViewModel
import com.krkhvr.gamemvvmca.presentation.ui.theme.Darkgray500
import com.krkhvr.gamemvvmca.presentation.ui.theme.Red500

@Composable
fun EditProfileContent(
    paddingValues: PaddingValues,
    editProfileViewModel: EditProfileViewModel = hiltViewModel()
) {

    /*val context = LocalContext.current

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            it?.let {
                editProfileViewModel.onGalleryResult(it)
            }
        }
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = {
            editProfileViewModel.onCameraResult(it)
        }
    )*/

    editProfileViewModel.resultingActivityHandler.Handle()

    val dialogState = remember { mutableStateOf(false) }
    DialogCapturePicture(
        status = dialogState,
        takePhoto = {
            editProfileViewModel.takePhoto()
        },
        pickImage = {
            editProfileViewModel.pickImage()
        }
    )

    Box(
        modifier = androidx.compose.ui.Modifier
            .padding(paddingValues)
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .height(220.dp)
                .background(Red500)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                if(editProfileViewModel.state.image != ""){
                    AsyncImage(
                        modifier = Modifier
                            .height(120.dp)
                            .width(120.dp)
                            .clip(CircleShape)
                            .clickable {
                                //galleryLauncher.launch("image/*")

                                /*val uri = ComposeFileProvider.getImageUri(context)
                                editProfileViewModel.imageUri = uri
                                cameraLauncher.launch(uri)*/

                                //editProfileViewModel.takePhoto()

                                dialogState.value = true
                            },
                        contentScale = ContentScale.Crop,
                        model = editProfileViewModel.state.image,
                        contentDescription = ""
                    )
                }else{
                    Image(
                        modifier = Modifier
                            .height(120.dp)
                            .clickable {
                                //galleryLauncher.launch("image/*")

                                /*val uri = ComposeFileProvider.getImageUri(context)
                                editProfileViewModel.imageUri = uri
                                cameraLauncher.launch(uri)*/

                                //editProfileViewModel.takePhoto()

                                dialogState.value = true
                            },
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = ""
                    )
                }
            }

        }

        Card(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 160.dp),
            colors = CardDefaults.cardColors(
                containerColor = Darkgray500 //Card background color
                //contentColor = Color.White  //Card content color,e.g.text
            )
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 30.dp)
            ) {
                Text(
                    modifier =
                    Modifier.padding(top = 40.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    text = "ACTUALIZACIÓN"
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Por favor ingresa estos datos para continuar",
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                DefaultEdiText(
                    modifier = Modifier.padding(top = 5.dp),
                    value = editProfileViewModel.state.username,
                    label = "Nombre de usuario",
                    icon = Icons.Default.Person,
                    keyboardType = KeyboardType.Text,
                    onValueChange = {
                        editProfileViewModel.onUsernameInput(it)
                        editProfileViewModel.validUsername()
                    },
                    errorMsg = editProfileViewModel.usernameErrorMsg
                )

                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 40.dp),
                    text = "Actualizar",
                    color = Red500,
                    icon = Icons.Default.ArrowForward,
                    onClick = {
                        editProfileViewModel.saveImage()
                    }
                )
            }
        }
    }
}