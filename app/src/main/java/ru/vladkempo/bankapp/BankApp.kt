package ru.vladkempo.bankapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp



@HiltAndroidApp
class BankApp : Application() { // Обязательно наследуемся от Application
}