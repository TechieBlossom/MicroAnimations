package com.techieblossom.kmicroanimations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.techieblossom.kmicroanimations.ui.theme.MicroAnimationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MicroAnimationsTheme {
                MicroAnimationApp()
            }
        }
    }
}