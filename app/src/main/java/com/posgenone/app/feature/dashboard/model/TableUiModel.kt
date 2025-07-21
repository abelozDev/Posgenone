package com.posgenone.app.feature.dashboard.model

// Table types and statuses
enum class TableStatus {
    FREE, RESERVED, OCCUPIED, INACTIVE
}

enum class TableType { TWO, FOUR }

// Table model (coordinates in grid cells)
data class TableUiModel(
    val id: Int,
    val number: String,
    val x: Int, // in grid cells
    val y: Int, // in grid cells
    val type: TableType,
    val status: TableStatus,
    val label: String? = null,
    val reservedTime: String? = null,
    val chairs: List<Boolean> = listOf() // true = occupied, false = free
) 