package com.krkhvr.gamemvvmca.presentation.screens.profile.components

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.krkhvr.gamemvvmca.R
import com.krkhvr.gamemvvmca.presentation.MainActivity
import com.krkhvr.gamemvvmca.presentation.components.DefaultButton
import com.krkhvr.gamemvvmca.presentation.navigation.DetailsScreens
import com.krkhvr.gamemvvmca.presentation.screens.profile.ProfileViewModel
import com.krkhvr.gamemvvmca.presentation.ui.theme.Red500

@Composable
fun ProfileContent(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box() {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = painterResource(id = R.drawable.background),
                contentScale = ContentScale.Crop,
                alpha = 0.6f,
                contentDescription = ""
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Bienvenido",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(50.dp))
                if (profileViewModel.userAccount.image != "") {
                    AsyncImage(
                        modifier = Modifier
                            .height(115.dp)
                            .width(115.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,
                        model = profileViewModel.userAccount.image,
                        contentDescription = ""
                    )
                } else {
                    Image(
                        modifier = Modifier.size(115.dp),
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = ""
                    )
                }

            }
        }

        Spacer(modifier = Modifier.height(55.dp))
        Text(
            text = profileViewModel.userAccount.username,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )

        Text(
            text = profileViewModel.userAccount.email,
            fontSize = 15.sp,
            fontStyle = FontStyle.Italic
        )

        Spacer(modifier = Modifier.height(20.dp))
        DefaultButton(
            modifier = Modifier.width(250.dp),
            text = "Editar datos",
            color = Color.DarkGray,
            icon = Icons.Default.Edit,
            onClick = {
                navHostController.navigate(
                    route = DetailsScreens.EditProfileScreen.setUser(profileViewModel.userAccount.toJson())
                )
            }
        )

        Spacer(modifier = Modifier.height(0.dp))
        DefaultButton(
            modifier = Modifier.width(250.dp),
            text = "Cerrar sesión",
            color = Red500,
            icon = Icons.Default.Close,
            onClick = {
                profileViewModel.logout()
                with(activity){
                    finish()
                    startActivity(Intent(activity, MainActivity::class.java))
                }
                /*navHostController.navigate(route = AuthScreens.LoginScreen.route) {
                    popUpTo(Graph.HOME) {
                        inclusive = true
                    }
                }*/
            }
        )
    }
}

/*@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewProfileContent(){
    GameMVVMCATheme(darkTheme = true) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ProfileContent()
        }
    }
}*/