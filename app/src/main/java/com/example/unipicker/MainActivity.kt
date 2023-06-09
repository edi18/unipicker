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
import com.example.unipicker.ui.question.QuestionViewModel


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
    viewModel: QuestionViewModel = viewModel(factory = QuestionViewModel.factory)
) {
    val navController = NavHostController(
        context = LocalContext.current
    )
    NavHost(
        navController = rememberNavController(),
        startDestination = unipickerScreen.Home.name,
        modifier = Modifier.padding(16.dp)
    ) {


        var state = mutableListOf<Int>(0,0,0,0,0,0)

        composable(route = unipickerScreen.Home.name){
            HomeScreen({})
        }

        composable(route = unipickerScreen.Question.name){

            val questionsList by viewModel.getAllQuestions().collectAsState(initial = emptyList())

            var currentQuestionIndex by remember { mutableStateOf(0) }

            val sendBackResponse = fun (n:Int) {
                state[questionsList[currentQuestionIndex].grouping] += n
                currentQuestionIndex++
            }

            QuestionScreen(question = questionsList[currentQuestionIndex], navigateToNextScreen = {}, sendBackResponse = sendBackResponse )
        }

        composable(route = unipickerScreen.Result.name){
            ResultScreen({}, state)
        }
    }
}