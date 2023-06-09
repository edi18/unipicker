package com.example.unipicker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.unipicker.ui.home.HomeScreen
import com.example.unipicker.ui.question.QuestionScreen
import com.example.unipicker.ui.result.ResultScreen
import com.example.unipicker.ui.theme.UnipickerTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.unipicker.data.Question
import com.example.unipicker.data.Results
import com.example.unipicker.ui.question.QuestionViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnipickerTheme (darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    UnipickerApp()
                }
            }
        }
    }
}

enum class unipickerScreen() {
    Home,
    Question,
    Result
}

@Composable
fun UnipickerApp(
    viewModel: QuestionViewModel = viewModel(factory = QuestionViewModel.factory),
    navController: NavHostController = rememberNavController()
) {
    val questionsList = viewModel.getAllQuestions()

    var state by remember {
        mutableStateOf(mutableListOf<Int>(0,0,0,0,0,0))
    }

//    var sndResults by remember {
//        mutableStateOf(Results(0, 0, 0))
//    }

    NavHost(
        navController = navController,
        startDestination = unipickerScreen.Home.name,
        modifier = Modifier.padding(16.dp)
    ) {
        composable(route = unipickerScreen.Home.name){
            HomeScreen({ navController.navigate(unipickerScreen.Question.name) })
        }

        composable(route = unipickerScreen.Question.name){
            var currentQuestionIndex by remember { mutableStateOf(0) }

            val sendBackResponse = fun (n:Int) {
                state[questionsList[currentQuestionIndex].grouping - 1] += n - 1
                currentQuestionIndex++
            }

            QuestionScreen(question = questionsList[currentQuestionIndex], navigateToNextScreen = {navController.navigate(unipickerScreen.Result.name)}, sendBackResponse = sendBackResponse, last = currentQuestionIndex == questionsList.size-1 )
        }

        composable(route = unipickerScreen.Result.name){
            ResultScreen(navigateToNextScreen = {navController.navigate(unipickerScreen.Home.name)}, state, viewModel)
        }
    }
}
