package com.example.marvelcomposee.ui.navigation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marvelcomposee.ui.theme.MarvelScreen

@Composable
fun DrawerContent(
    drawerOptions: List<NavItem>,
    selectedIndex: Int,
    onOptionClick : (NavItem) -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.secondary
                    )
                )
            )
            .height(200.dp)
            .fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(16.dp))
    drawerOptions.forEachIndexed { index, navItem ->
        val selected = selectedIndex == index
        val colors = MaterialTheme.colorScheme

        Row (
            modifier = Modifier
                .clickable { onOptionClick(navItem) }
                .fillMaxWidth()
                .padding(8.dp, 4.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(color = if (selected) colors.primary.copy(alpha = 0.12f) else colors.surface)
                .padding(12.dp)
        ){
            Icon(
                imageVector = navItem.icon ,
                tint = if(selected) colors.primary else colors.onSurface.copy(
                    alpha = ContentAlpha.medium
                ),
                contentDescription = navItem.name
            )
            Spacer(modifier = Modifier.width(24.dp))
            Text(
                text = stringResource(id = navItem.title),
                color = if(selected) colors.primary else colors.onSurface,
                style =
                MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DrawerContentPreview() {
   MarvelScreen {
       Column {
           DrawerContent(
               drawerOptions = listOf(NavItem.HOME, NavItem.SETTINGS),
               selectedIndex = 0,
               onOptionClick = { }
           )
       }
   }
    
}