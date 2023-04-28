package com.mineinabyss.launchy.ui.screens.main

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mineinabyss.launchy.LocalLaunchyState

@Composable
fun UpdateInfoButton() {
    val state = LocalLaunchyState
    var toggled by remember { mutableStateOf(false) }
    Button(
        onClick = { toggled = !toggled },
        shape = AbsoluteRoundedCornerShape(corner = CornerSize(5.dp)),
    ) {
        Column {
            Row {
                Text("${state.queuedDownloads.size + state.queuedDeletions.size} Updates")
            }
            AnimatedVisibility(
                toggled,
                enter = expandIn(tween(200)) + fadeIn(tween(200, 100)),
                exit = fadeOut() + shrinkOut(tween(200, 100))
            ) {
                Column {
                    Spacer(Modifier.height(25.dp))
                    InfoText(
                        shown = !state.fabricUpToDate,
                        desc = "Install Fabric",
                    )
                    InfoText(
                        shown = state.updatesQueued,
                        desc = "Update:",
                        extra = state.queuedUpdates.size.toString()
                    )
                    InfoText(
                        shown = state.installsQueued,
                        desc = "Download:",
                        extra = state.queuedInstalls.size.toString()
                    )
                    InfoText(
                        shown = state.deletionsQueued,
                        desc = "Remove:",
                        extra = state.queuedDeletions.size.toString()
                    )
                }
            }
        }
    }
}

@Composable
fun InfoText(shown: Boolean, desc: String, extra: String = "") {
    if (shown) Row(verticalAlignment = Alignment.CenterVertically) {
        Text(desc, Modifier.padding(5.dp))
        Text(extra)
    }
}


