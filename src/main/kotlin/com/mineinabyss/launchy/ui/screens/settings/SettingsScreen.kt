package com.mineinabyss.launchy.ui.screens.settings

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mineinabyss.launchy.LocalLaunchyState
import com.mineinabyss.launchy.data.Constants.SETTINGS_HORIZONTAL_PADDING

@Composable
@Preview
fun SettingsScreen() {
    val state = LocalLaunchyState
    Scaffold(
//        containerColor =  MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f),
        bottomBar = { InfoBar() },
    ) { paddingValues ->
        Box(Modifier.padding(paddingValues)) {
            Box(Modifier.padding(horizontal = SETTINGS_HORIZONTAL_PADDING)) {
                val lazyListState = rememberLazyListState()
                LazyColumn(Modifier.fillMaxSize().padding(end = 15.dp), lazyListState) {
                    item { Spacer(Modifier.height(4.dp)) }
                    items(state.versions.modGroups.toList()) { (group, mods) ->
                        ModGroup(group, mods)
                    }
                }
                VerticalScrollbar(
                    modifier = Modifier.fillMaxHeight().align(Alignment.CenterEnd).padding(vertical = 2.dp),
                    adapter = rememberScrollbarAdapter(lazyListState)
                )
            }
        }
    }
}
