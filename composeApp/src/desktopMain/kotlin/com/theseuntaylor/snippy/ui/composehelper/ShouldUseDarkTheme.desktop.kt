package com.theseuntaylor.snippy.ui.composehelper

import androidx.compose.runtime.Composable
import java.awt.Toolkit
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Locale
import javax.swing.UIManager

@Composable
actual fun shouldUseDarkTheme(): Boolean {
    val osName = System.getProperty("os.name", "").lowercase(Locale.getDefault())
    return when {
        osName.contains("mac") || osName.contains("darwin") -> shouldUseDarkThemeForMac()
        osName.contains("win") -> shouldUseDarkThemeForWindows() // You can implement this similarly
        else -> shouldUseDarkThemeGenericSwing() // For Linux or other OSes
    }
}

@Composable
private fun shouldUseDarkThemeForMac(): Boolean {
    try {
        // Try system property first (less overhead)
        val appearanceProperty = System.getProperty("apple.awt.application.appearance")
        if (appearanceProperty != null) {
            return appearanceProperty.lowercase(Locale.getDefault()).contains("dark")
        }

        // Fallback: Execute `defaults read -g AppleInterfaceStyle`
        val process = Runtime.getRuntime().exec(arrayOf("defaults", "read", "-g", "AppleInterfaceStyle"))
        val reader = BufferedReader(InputStreamReader(process.inputStream))
        val line = reader.readLine()
        val exitCode = process.waitFor() // Wait for the process to complete
        reader.close()

        if (exitCode == 0 && "Dark".equals(line, ignoreCase = true)) {
            return true
        }
    } catch (e: Exception) {
        System.err.println("Failed to detect macOS dark theme: ${e.message}")
    }
    // Fallback to generic Swing check if macOS specific methods fail or OS is not Mac
    return shouldUseDarkThemeGenericSwing()
}

@Composable
private fun shouldUseDarkThemeForWindows(): Boolean {
    // Placeholder: Implement Windows-specific dark theme detection if needed.
    // This could involve checking registry keys (more complex, requires a library or JNA)
    // or relying on the Look and Feel.
    // For now, falling back to generic.
    return try {
        // A common way to check is by looking at the default background color
        // of UI elements. This is a heuristic.
        val defaultBackground = UIManager.getColor("Panel.background") // For Swing L&F
        val desktopBackground = Toolkit.getDefaultToolkit()
            .getDesktopProperty("sun.awt.desktopBackgroundColor") // Another potential way

        // Heuristic: If the background is dark, assume dark theme.
        // You might need to adjust the threshold.
        val isDefaultDark =
            defaultBackground?.let { (it.red * 0.299 + it.green * 0.587 + it.blue * 0.114) < 128 }
                ?: false
        val isDesktopDark = desktopBackground?.let { color ->
            // desktopBackground can be an int (ARGB) or Color object
            val c = color as? java.awt.Color ?: java.awt.Color(color as Int, true)
            (c.red * 0.299 + c.green * 0.587 + c.blue * 0.114) < 128
        } ?: false
        isDefaultDark || isDesktopDark
    } catch (e: Exception) {
        System.err.println("Failed to detect Windows dark theme: ${e.message}")
        false // Default to light on error
    }
}

// Generic Swing-based heuristic as a general fallback
@Composable
private fun shouldUseDarkThemeGenericSwing(): Boolean {
    return try {
        val defaultBackground = UIManager.getColor("Panel.background")
        defaultBackground?.let { (it.red * 0.299 + it.green * 0.587 + it.blue * 0.114) < 128 } ?: false
    } catch (e: Exception) {
        System.err.println("Failed to detect generic Swing dark theme: ${e.message}")
        false // Default to light on error
    }
}

// Helper function (already present in your file) [1]
internal fun isDesktopFromApple(): Boolean {
    val osName = System.getProperty("os.name", "").lowercase(Locale.getDefault())
    return osName.contains("mac") || osName.contains("darwin")
}

