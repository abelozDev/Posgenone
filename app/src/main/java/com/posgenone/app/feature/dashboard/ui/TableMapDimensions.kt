package com.posgenone.app.feature.dashboard.ui

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.posgenone.app.feature.dashboard.model.TableType

class TableMapDimensions {
    companion object {
        val ROOM_WIDTH_DP = 2000.dp
        val ROOM_HEIGHT_DP = 2000.dp
    }
    // Table sizes (in dp)
    fun tableWidth(type: TableType): Dp = if (type == TableType.FOUR) 180.dp else 120.dp
    fun tableHeight(type: TableType): Dp = 120.dp

    // Borders and corners
    val borderWidth = 12.dp
    val corner = 18.dp

    // Inner paddings
    val contentPaddingStart = borderWidth + 12.dp
    val contentPadding = 12.dp

    // Font sizes (reduced)
    val numberFontSize = 18.sp
    val labelFontSize = 14.sp
    val reservedTimeFontSize = 12.sp

    // Spacers
    val spacer1 = 16.dp
    val spacer2 = 1.dp // minimal space between label and reservedTime

    // Chair sizes (in dp)
    val chairWidth = 40.dp
    val chairHeight = 16.dp
    val chairRadius = 8.dp
    val chairOffset = 20.dp
} 