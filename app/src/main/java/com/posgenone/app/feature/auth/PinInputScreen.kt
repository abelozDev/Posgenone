package com.posgenone.app.feature.auth

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.material3.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.posgenone.app.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PinInputScreen(navController: NavController) {
    val pinLength = 4
    val correctPin = "1234"
    val focusManager = LocalFocusManager.current

    var pin by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF222125))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back_icon),
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { navController.popBackStack() }
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Choosing a Role",
                    fontSize = 18.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.size(24.dp))
            }

            Canvas(modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)) {
                drawLine(
                    color = Color(0xFF3A3743),
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = size.height
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 24.dp, bottom = 6.dp, start = 24.dp, end = 24.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.White)) {
                            append("Entrance Under\nthe Role of: ")
                        }
                        withStyle(style = SpanStyle(color = Color(0xFF3C86FF))) {
                            append("Waiter")
                        }
                    },
                    fontSize = 24.sp,
                    lineHeight = 36.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Text(
                text = "Please enter the pin code",
                color = Color.White,
                fontSize = 14.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(top = 0.dp, bottom = 12.dp, start = 24.dp, end = 24.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(horizontal = 24.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    repeat(pinLength) { index ->
                        Box(
                            modifier = Modifier
                                .size(width = 44.dp, height = 48.dp)
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clickable { focusRequester.requestFocus() },
                            contentAlignment = Alignment.Center
                        ) {
                            if (index < pin.length) {
                                Text(
                                    text = "*",
                                    fontSize = 36.sp,
                                    color = Color.Black,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }

                OutlinedTextField(
                    value = pin,
                    onValueChange = {
                        if (it.length <= pinLength && it.all { c -> c.isDigit() }) {
                            pin = it
                            errorMessage = ""

                            if (it.length == pinLength) {
                                focusManager.clearFocus()
                                isLoading = true
                                scope.launch {
                                    delay(1000)
                                    isLoading = false
                                    if (pin == correctPin) {
                                        navController.navigate("loading") {
                                            popUpTo("pin") { inclusive = true }
                                        }
                                    } else {
                                        errorMessage = "Incorrect PIN"
                                        pin = ""
                                    }
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .size(1.dp)
                        .alpha(0f),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    singleLine = true
                )

                if (errorMessage.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = errorMessage, color = Color.Red, fontSize = 14.sp)
                }

                if (isLoading) {
                    Spacer(modifier = Modifier.height(24.dp))
                    CircularProgressIndicator(color = Color.White)
                }
            }
        }
    }
}