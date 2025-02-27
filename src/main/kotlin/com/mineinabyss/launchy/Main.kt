package com.mineinabyss.launchy

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.mineinabyss.launchy.data.Config
import com.mineinabyss.launchy.data.Dirs
import com.mineinabyss.launchy.data.Versions
import com.mineinabyss.launchy.logic.LaunchyState
import com.mineinabyss.launchy.ui.LaunchyTypography
import com.mineinabyss.launchy.ui.smpColorScheme
import com.mineinabyss.launchy.ui.screens.Screens
import com.mineinabyss.launchy.ui.state.TopBarProvider
import com.mineinabyss.launchy.ui.state.TopBarState
import com.mineinabyss.launchy.util.OS

private val LaunchyStateProvider = compositionLocalOf<LaunchyState> { error("No local versions provided") }
val LocalLaunchyState: LaunchyState
    @Composable
    get() = LaunchyStateProvider.current

fun main() {
    application {
        val windowState = rememberWindowState(placement = WindowPlacement.Floating)
        val icon = painterResource("smp_icon.png")
        val launchyState by produceState<LaunchyState?>(null) {
            val config = Config.read()
            val versions = Versions.readLatest(config.downloadUpdates)
            value = LaunchyState(config, versions)
        }
        val onClose: () -> Unit = {
            exitApplication()
            launchyState?.save()
        }
        Window(
            state = windowState,
            title = "SwaggySMP Installer",
            icon = icon,
            onCloseRequest = onClose,
            undecorated = true,
            transparent = OS.get() == OS.WINDOWS, // Windows 11 shows a white bar on the bottom without this
        ) {
            val topBarState = remember { TopBarState(onClose, windowState, this) }
            val ready = launchyState != null
            val scheme = smpColorScheme()
            MaterialTheme(colorScheme = scheme, typography = LaunchyTypography) {
                CompositionLocalProvider(TopBarProvider provides topBarState) {
                    Scaffold {
                        AnimatedVisibility(!ready, exit = fadeOut()) {
                            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Text("Getting latest updates...")
                            }
                        }
                        AnimatedVisibility(ready, enter = fadeIn()) {
                            CompositionLocalProvider(
                                LaunchyStateProvider provides launchyState!!,
                            ) {
                                Dirs.createDirs()
                                Screens()
                            }
                        }
                    }
                }
            }
        }
    }
}
