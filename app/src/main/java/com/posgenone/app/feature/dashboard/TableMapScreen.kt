package com.posgenone.app.feature.dashboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.zIndex
import com.posgenone.app.feature.dashboard.model.TableUiModel
import com.posgenone.app.feature.dashboard.model.TableType
import com.posgenone.app.feature.dashboard.model.TableStatus
import com.posgenone.app.feature.dashboard.ui.TableMapDimensions
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Alignment
import com.posgenone.app.R


@Composable
fun TableMapScreen(
    tables: List<TableUiModel>
) {
    var selectedTableId by remember { mutableStateOf<Int?>(null) }
    val scrollStateX = rememberScrollState()
    val scrollStateY = rememberScrollState()
    val dimensions = remember { TableMapDimensions() }
    val roomWidth = TableMapDimensions.ROOM_WIDTH_DP
    val roomHeight = TableMapDimensions.ROOM_HEIGHT_DP

    Box(
        modifier = Modifier
            .verticalScroll(scrollStateY)
            .horizontalScroll(scrollStateX)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .size(roomWidth, roomHeight)
                .background(Color(0x992E2E2E))
        ) {
            Canvas(modifier = Modifier.matchParentSize()) {
                val color = Color(0xFF222125)
                val gridStepPx = with(this) { 50.dp.toPx() } // smaller grid step
                val countX = (size.width / gridStepPx).toInt()
                val countY = (size.height / gridStepPx).toInt()
                for (i in 0..countX) {
                    val pos = i * gridStepPx
                    drawLine(color, Offset(pos, 0f), Offset(pos, size.height), strokeWidth = 2f)
                }
                for (i in 0..countY) {
                    val pos = i * gridStepPx
                    drawLine(color, Offset(0f, pos), Offset(size.width, pos), strokeWidth = 2f)
                }
            }
            tables.forEach { table ->
                TableComposable(
                    table = table,
                    selected = selectedTableId == table.id,
                    onClick = {
                        selectedTableId = if (selectedTableId == table.id) null else table.id
                    },
                    dimensions = dimensions
                )
            }
        }
    }
}

@Composable
fun TableComposable(
    table: TableUiModel,
    selected: Boolean,
    onClick: () -> Unit,
    dimensions: TableMapDimensions
) {
    val borderColor = when (table.status) {
        TableStatus.RESERVED -> Color(0xFFFEC045)
        TableStatus.OCCUPIED -> Color(0xFF3C86FF)
        else -> Color(0xFF95959D)
    }
    val tableColor = Color(0xFF424E48)
    val textColor = Color.White
    val statusColor = Color(0xFF95959D)

    val tableWidth = dimensions.tableWidth(table.type)
    val tableHeight = dimensions.tableHeight(table.type)

    val baseOffsetX = table.x.dp
    val baseOffsetY = table.y.dp
    val horizontalPadding = 20.dp
    val verticalPadding = 28.dp

    Box(
        modifier = Modifier
            .offset(table.x.dp, table.y.dp)
            .size(dimensions.tableWidth(table.type), dimensions.tableHeight(table.type))
            .zIndex(if (selected) 2f else 1f)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        // Table with left border
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(tableColor, shape = RoundedCornerShape(dimensions.corner))
        ) {
            // Left border
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(dimensions.borderWidth)
                    .background(
                        borderColor,
                        shape = RoundedCornerShape(
                            topStart = dimensions.corner,
                            bottomStart = dimensions.corner
                        )
                    )
                    .align(Alignment.CenterStart)
            )
        }
        // Content inside table
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = dimensions.contentPaddingStart,
                    top = dimensions.contentPadding,
                    end = dimensions.contentPadding,
                    bottom = dimensions.contentPadding
                ),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = table.number,
                color = textColor,
                fontWeight = FontWeight.Bold,
                fontSize = dimensions.numberFontSize,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(dimensions.spacer1))
            Text(
                text = table.label
                    ?: if (table.status == TableStatus.FREE) "Free" else "Checked-in",
                color = statusColor,
                fontSize = dimensions.labelFontSize,
                maxLines = 1
            )
            if (table.reservedTime != null) {
                Spacer(modifier = Modifier.height(dimensions.spacer2))
                Text(
                    text = table.reservedTime,
                    color = statusColor,
                    fontSize = dimensions.reservedTimeFontSize,
                    maxLines = 1
                )
            }
        }
        // Chairs
        DrawChairs(table, dimensions)
    }
    if (selected) {
        Box(
            modifier = Modifier
                .offset(baseOffsetX - horizontalPadding, baseOffsetY - verticalPadding)
                .size(tableWidth + horizontalPadding * 2, tableHeight + verticalPadding * 2)
                .zIndex(10f)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val stroke = Stroke(
                    width = 2.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
                )
                drawRoundRect(
                    color = Color(0x335C5C64), // fill (20%)
                    size = size,
                    cornerRadius = CornerRadius(12.dp.toPx()),
                    style = Fill
                )
                drawRoundRect(
                    color = Color(0xFF3A3743), // border
                    size = size,
                    cornerRadius = CornerRadius(12.dp.toPx()),
                    style = stroke
                )
            }
            Image(
                painter = painterResource(id = R.drawable.edit_button),
                contentDescription = "Edit Table",
                modifier = Modifier
                    .size(36.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = 8.dp, y = -8.dp)
                    .zIndex(11f)
                    .clickable { /* TODO: edit click */ }
            )
        }
    }
}

@Composable
fun DrawChairs(table: TableUiModel, dimensions: TableMapDimensions) {
    val chairColorOccupied = Color(0xFF3C86FF)
    val chairColorFree = Color(0xFF95959D)
    val tableWidth = dimensions.tableWidth(table.type)
    val tableHeight = dimensions.tableHeight(table.type)
    val chairWidth = dimensions.chairWidth
    val chairHeight = dimensions.chairHeight
    val offset = 8.dp
    val chairs = table.chairs

    Box(modifier = Modifier.fillMaxSize()) {
        if (table.type == TableType.TWO && chairs.size == 2) {
            // Top
            ChairEllipse(
                color = if (chairs[0]) chairColorOccupied else chairColorFree,
                x = (tableWidth - chairWidth) / 2,
                y = -chairHeight - offset,
                width = chairWidth,
                height = chairHeight,
                radius = dimensions.chairRadius
            )
            // Bottom
            ChairEllipse(
                color = if (chairs[1]) chairColorOccupied else chairColorFree,
                x = (tableWidth - chairWidth) / 2,
                y = tableHeight + offset,
                width = chairWidth,
                height = chairHeight,
                radius = dimensions.chairRadius
            )
        } else if (table.type == TableType.FOUR && chairs.size == 4) {
            // Top left
            ChairEllipse(
                color = if (chairs[0]) chairColorOccupied else chairColorFree,
                x = tableWidth / 4 - chairWidth / 2,
                y = -chairHeight - offset,
                width = chairWidth,
                height = chairHeight,
                radius = dimensions.chairRadius
            )
            // Top right
            ChairEllipse(
                color = if (chairs[1]) chairColorOccupied else chairColorFree,
                x = tableWidth * 3 / 4 - chairWidth / 2,
                y = -chairHeight - offset,
                width = chairWidth,
                height = chairHeight,
                radius = dimensions.chairRadius
            )
            // Bottom left
            ChairEllipse(
                color = if (chairs[2]) chairColorOccupied else chairColorFree,
                x = tableWidth / 4 - chairWidth / 2,
                y = tableHeight + offset,
                width = chairWidth,
                height = chairHeight,
                radius = dimensions.chairRadius
            )
            // Bottom right
            ChairEllipse(
                color = if (chairs[3]) chairColorOccupied else chairColorFree,
                x = tableWidth * 3 / 4 - chairWidth / 2,
                y = tableHeight + offset,
                width = chairWidth,
                height = chairHeight,
                radius = dimensions.chairRadius
            )
        }
    }
}


@Composable
fun ChairEllipse(
    color: Color,
    x: Dp,
    y: Dp,
    width: Dp,
    height: Dp,
    radius: Dp
) {
    Box(
        modifier = Modifier
            .offset(x, y)
            .size(width, height)
            .clip(RoundedCornerShape(radius))
            .background(color)
    )
}