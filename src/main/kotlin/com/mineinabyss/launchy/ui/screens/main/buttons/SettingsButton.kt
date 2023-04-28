package com.mineinabyss.launchy.ui.screens.main.buttons

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mineinabyss.launchy.ui.screens.Screen
import com.mineinabyss.launchy.ui.screens.screen

@Composable
fun SettingsButton() {
    Button(
        onClick = { screen = Screen.Settings },
        shape = AbsoluteRoundedCornerShape(corner = CornerSize(5.dp)),
    ) {
        Text("Settings")
    }
}
