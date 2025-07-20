package com.posgenone.app.feature.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.posgenone.app.R
import androidx.compose.ui.text.style.TextAlign


@Composable
fun TableDashboardScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF222125))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.burger_button),
                    contentDescription = "Menu",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Miles M.",
                    fontSize = 18.sp,
                    color = Color(0xFF3C86FF),
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.profile_button),
                    contentDescription = "Profile",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Top control row with stats and buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TableStatusIndicator(
                    modifier = Modifier.padding(end = 24.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.main_menu_button),
                    contentDescription = "Profile",
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Image(
                    painter = painterResource(id = R.drawable.map_button),
                    contentDescription = "Map",
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f)) // Push bottom bar down

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(108.dp)
                    .padding(horizontal = 24.dp)
                    .background(Color(0xFF222125).copy(alpha = 0.6f), RoundedCornerShape(71.dp))
                    .border(2.dp, Color(0xFF3A3743), RoundedCornerShape(71.dp)),
                contentAlignment = Alignment.Center
            ) {
                Box(modifier = Modifier.fillMaxSize()) {

                    val labelTextStyle = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.2.sp
                    )

                    // Tables (active)
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 24.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.tables_button_icon),
                            contentDescription = "Tables",
                            modifier = Modifier.size(36.dp)
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Tables",
                            style = labelTextStyle.copy(color = Color(0xFFC9AA7F)),
                            fontSize = 16.sp
                        )
                    }

                    // Orders
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.orders_button_icon),
                            contentDescription = "Orders",
                            modifier = Modifier.size(36.dp)
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Orders",
                            style = labelTextStyle,
                            fontSize = 16.sp
                        )
                    }

                    // Reports
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 24.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.reports_button_icon),
                            contentDescription = "Reports",
                            modifier = Modifier.size(36.dp)
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Reports",
                            style = labelTextStyle,
                            fontSize = 16.sp
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.height(24.dp)) // Bottom padding
        }
    }
}

@Composable
fun TableStatusIndicator(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(48.dp)
            .width(184.dp)
            .background(
                color = Color(0xFF5C5C64).copy(alpha = 0.2f),
                shape = RoundedCornerShape(83.dp)
            )
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(Color(0xFF95959D), CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(16.dp)
                        .background(Color(0xFF95959D))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "6",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(Color(0xFF3C86FF), CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(16.dp)
                        .background(Color(0xFF95959D))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "12",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    }
}

