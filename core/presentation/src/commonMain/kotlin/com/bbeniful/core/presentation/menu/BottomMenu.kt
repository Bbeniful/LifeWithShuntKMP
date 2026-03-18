package com.bbeniful.core.presentation.menu

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import com.bbeniful.core.presentation.extension.noRippleClickable
import com.bbeniful.core.presentation.token.Colors
import com.bbeniful.feature.api.nav.SymptomNavRoute
import com.bbeniful.nav.HomeRoute
import lifewithshunt.core.presentation.generated.resources.Res
import lifewithshunt.core.presentation.generated.resources.icon_home
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


enum class BottomItem(
    val icon: DrawableResource,
    val title: String,
    val route: Any
) {
    Home(
        icon = Res.drawable.icon_home,
        title = "Home",
        route = HomeRoute
    ),
    Symptom(
        icon = Res.drawable.icon_home,
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
            .height(120.dp)
            .padding(bottom = 30.dp, top = 20.dp)
            .padding(horizontal = 20.dp)
            .background(color = Color.Black.copy(alpha = 0.2f), shape = RoundedCornerShape(40.dp))
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
    val selectedIconColor by animateColorAsState(
        targetValue = if (isSelected) Colors.HighElement else Color.White.copy(alpha = 0.6f),
        label = ""
    )
    val iconSize by animateDpAsState(
        targetValue = if (isSelected) 25.dp else 20.dp,
        label = ""
    )

    Column(
        modifier = Modifier
            .height(120.dp)
            .noRippleClickable {
                onClick(item)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 5.dp,
            alignment = Alignment.CenterVertically
        )
    ) {

        Icon(
            modifier = Modifier.size(iconSize),
            painter = painterResource(item.icon),
            contentDescription = item.title,
            tint = selectedIconColor
        )
        Text(
            modifier = Modifier
                .graphicsLayer {
                    scaleX = selectedSize
                    scaleY = selectedSize
                },
            text = item.title,
            fontSize = 16.sp,
            color = Color.White.copy(alpha = selectedAlpha)
        )
    }
}