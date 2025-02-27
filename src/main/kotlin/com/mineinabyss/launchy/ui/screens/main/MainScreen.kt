package com.mineinabyss.launchy.ui.screens.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mineinabyss.launchy.LocalLaunchyState
import com.mineinabyss.launchy.ui.screens.main.buttons.InstallButton
import com.mineinabyss.launchy.ui.screens.main.buttons.SettingsButton
import com.mineinabyss.launchy.ui.state.windowScope
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

val showComingSoonDialog = mutableStateOf(false)

@ExperimentalComposeUiApi
@Preview
@Composable
fun MainScreen() {
    val state = LocalLaunchyState

    Box {
        BackgroundImage(windowScope)

        Column(
            modifier =
            Modifier.align(Alignment.Center)
                .heightIn(0.dp, 550.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoLarge(Modifier.weight(3f))
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().weight(1f),
            ) {
//                PlayButton(!state.isDownloading && !state.operationsQueued && state.minecraftValid)
                InstallButton(!state.isDownloading && state.operationsQueued && state.minecraftValid)
                AnimatedVisibility(state.operationsQueued) {
                    UpdateInfoButton()
                }
//                NewsButton(hasUpdates = true)
//                Spacer(Modifier.width(10.dp))
                SettingsButton()
            }
            Text(
                "Developed by LabKit Studios\nImage by Interstellar Cow#8015",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                fontSize = 10.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        FirstLaunchDialog()

        HandleImportSettings()

        if (showComingSoonDialog.value) ComingSoonDialog()
    }
}
