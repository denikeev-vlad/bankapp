package ru.vladkempo.bankapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.vladkempo.bankapp.presentation.ui.OperationDetailScreen
import ru.vladkempo.bankapp.presentation.ui.OperationScreen

@Composable
fun AppNavigation() {
    //Создаем контроллер, который управляет перемещением
    val navController = rememberNavController()

    //Описчвыаем граф переходов
    NavHost(
        navController = navController,
        startDestination = Screen.OperationList.route //Начинаем со списка
    ) {
        //Экран списка операций
        composable(Screen.OperationList.route) {
            OperationScreen(
                onOperationClick = { operationId ->
                    navController.navigate(Screen.OperationDetails.createRoute(operationId))

                }
            )
        }
        //Экран деталей операций

        composable(
            route = Screen.OperationDetails.route,
            arguments = listOf(navArgument("operationId") { type = NavType.IntType })
        ) {
            backStackEntry ->
            //Достаем переданый ID
            val operationId =  backStackEntry.arguments?.getInt("operationId") ?: 0

            //Временно просто текст, пока не создали экран
            OperationDetailScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun OperationDetailsPlaceHolder(id: Int) {
    androidx.compose.material3.Text("Детали операций #$id (Скоро здесь будет все")
}
