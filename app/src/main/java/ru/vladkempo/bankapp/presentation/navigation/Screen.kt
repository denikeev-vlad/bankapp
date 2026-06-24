package ru.vladkempo.bankapp.presentation.navigation

sealed class Screen(
    val route: String
) {
    //Главный экран со списком
    object OperationList : Screen(OPERATION_LIST_ROUTE)
    //Экран деталей. {operationId} - это переменная, которую мы передатим
    object OperationDetails : Screen("$OPERATION_DETAILS_ROUTE/{$OPERATION_ID_ARG}") {
        fun createRoute(operationId: Int) = "$OPERATION_DETAILS_ROUTE/$operationId"
    }

    companion object {
        private const val OPERATION_ID_ARG = "operationId"
        private const val OPERATION_DETAILS_ROUTE = "operation_details"
        private const val OPERATION_LIST_ROUTE = "operation_list"
    }

}