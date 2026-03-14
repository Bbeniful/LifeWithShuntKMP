package com.bbeniful.core.presentation.menu

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bbeniful.feature.api.nav.SymptomNavRoute
import com.bbeniful.nav.HomeRoute


enum class BottomItem(
    //val icon: ImageVector,
    val title: String,
    val route: Any
) {
    Home(
        title = "Home",
        route = HomeRoute
    ),
    Symptom(
        title = "Symptom",
        route = SymptomNavRoute
    )
}

@Composable
fun BottomMenu(
    modifier: Modifier = Modifier,
    items: List<BottomItem>,
    backstack: SnapshotStateList<Any>
) {

    var selectedItem by mutableStateOf(BottomItem.Home)

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(bottom = 30.dp, top = 20.dp)
            .padding(horizontal = 20.dp)
            .background(color = Color.Black.copy(alpha = 0.2f), shape = RoundedCornerShape(20.dp))
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(20.dp)),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items(items) { item ->
            BottomMenuItem(
                item = item,
                isSelected = selectedItem == item,
                onClick = { selected ->
                    selectedItem = selected
                    backstack.add(selected.route)
                }
            )
        }
    }
}

@Composable
private fun BottomMenuItem(
    item: BottomItem,
    isSelected: Boolean,
    onClick: (BottomItem) -> Unit
) {
    val selectedAlpha by animateFloatAsState(
        targetValue = if (isSelected) 1f else 0.6f,
        label = ""
    )
    val selectedSize by animateFloatAsState(
        targetValue = if (isSelected) 1.2f else 1f,
        label = ""
    )
    Column(
        modifier = Modifier
            .height(100.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onClick(item)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .graphicsLayer {
                    scaleX = selectedSize
                    scaleY = selectedSize
                },
            text = item.title,
            fontSize = 16.sp,
            color = Color.Black.copy(alpha = selectedAlpha)
        )
    }
}