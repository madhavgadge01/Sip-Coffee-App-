package com.example.sip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.sip.HomeScreen.MyApp
import com.example.sip.HomeScreen.SecondScreen
import com.example.sip.ui.theme.SipTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SipTheme {

                MyApp()
            }
        }
    }
}

