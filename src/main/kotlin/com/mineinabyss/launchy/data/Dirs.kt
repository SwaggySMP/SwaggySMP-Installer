package com.mineinabyss.launchy.data

import com.mineinabyss.launchy.util.OS
import kotlin.io.path.*

object Dirs {
    val home = Path(System.getProperty("user.home"))
    val minecraft = when (OS.get()) {
        OS.WINDOWS -> Path(System.getenv("APPDATA")) / ".minecraft"
        OS.MAC -> Path(System.getProperty("user.home")) / "Library/Application Support/minecraft"
        OS.LINUX -> Path(System.getProperty("user.home")) / ".minecraft"
    }

    val swaggysmp = when (OS.get()) {
        OS.WINDOWS -> Path(System.getenv("APPDATA")) / ".swaggysmp"
        OS.MAC -> Path(System.getProperty("user.home")) / "Library/Application Support/swaggysmp"
        OS.LINUX -> Path(System.getProperty("user.home")) / ".swaggysmp"
    }
    val mods = swaggysmp / "mods"
    val tmp = swaggysmp / ".tmp"

    val config = when (OS.get()) {
        OS.WINDOWS -> Path(System.getenv("APPDATA"))
        OS.MAC -> Path(System.getProperty("user.home")) / "Library/Application Support"
        OS.LINUX -> home / ".config"
    } / "swaggysmp"

    val configFile = config / "smp-launcher.yml"
    val versionsFile = config / "smp-versions.yml"

    fun createDirs() {
        config.createDirectories()
        swaggysmp.createDirectories()
        mods.createDirectories()
        tmp.createDirectories()
    }

    fun createConfigFiles() {
        if (configFile.notExists())
            configFile.createFile().writeText("{}")
        if (versionsFile.notExists())
            versionsFile.createFile().writeText("{}")
    }
}
