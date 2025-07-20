package com.posgenone.app.feature.auth

import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.posgenone.app.R

@Composable
fun RoleSelectionScreen(onContinue: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .background(Color(0xFF222125)),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "Welcome to POS GenOne",
            fontSize = 24.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Please select a role to log in to the app",
            fontSize = 14.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(32.dp))

        RoleCard(onClick = onContinue)
    }
}

@Composable
fun RoleCard(onClick: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(202.dp)
            .background(
                color = Color(0x9918181C), // 60% прозрачности
                shape = RoundedCornerShape(20.dp)
            )
            .padding(20.dp)
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = R.drawable.waiter),
            contentDescription = "Waiter Icon",
            modifier = Modifier.size(42.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Waiter",
            fontSize = 16.sp,
            color = Color(0xFF3C86FF),
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Access and manage table orders, add items, apply discounts, split checks, and take payments — everything needed for day-to-day service.",
            fontSize = 12.sp,
            color = Color(0xFF95959D),
            lineHeight = 16.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Continue →",
            fontSize = 12.sp,
            color = Color(0xFFC9AA7F),
            fontWeight = FontWeight.Medium
        )
    }
}

