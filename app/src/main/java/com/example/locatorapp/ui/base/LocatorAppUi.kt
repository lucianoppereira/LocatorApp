package com.example.locatorapp.ui.base

import androidx.compose.runtime.Composable
import com.example.locatorapp.ui.layout.LocatorNarrowLayout
import com.example.locatorapp.ui.layout.LocatorWideLayout

@Composable
fun LocatorAppUi(isWideDisplay: Boolean) {
    LocatorAppContent(isWideDisplay)
}

@Composable
fun LocatorAppContent(isWideDisplay: Boolean) {
    if (isWideDisplay) {
        LocatorWideLayout()
    } else {
        LocatorNarrowLayout()
    }
}
