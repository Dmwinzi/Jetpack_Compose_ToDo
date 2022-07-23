package com.example.todo

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.textInputServiceFactory
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todo.data.TaskEntity
import com.example.todo.data.TaskviewModel
import com.example.todo.ui.theme.TODOTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val taskviewModel  = ViewModelProvider(this).get(TaskviewModel::class.java)
        setContent {
            TODOTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = { TopAppBar(title = { Text(text = "TO-DO")})},
                        floatingActionButton = { FloatingActionButton(onClick = {
                            val intent  = Intent(this,MainActivity::class.java)
                            startActivity(intent)
                        }) {
                            Icon(painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24), contentDescription =null )
                        }}
                    ) {

                        var title  = remember { mutableStateOf("")}
                        var task  = remember { mutableStateOf("")}
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            OutlinedTextField(value =title.value , onValueChange ={title.value = it}, label = { Text(
                                text = "Title"
                            )} )
                            Spacer(modifier = Modifier.height(20.dp))
                            OutlinedTextField(value = task.value, onValueChange ={task.value = it} , label = { Text(text = "Task")})
                            Spacer(modifier = Modifier.height(20.dp))
                            Button(onClick = {
                                taskviewModel.inserttask(TaskEntity(title = title.value, task = task.value))
                                Toast.makeText(applicationContext,"Inserted succesfully",Toast.LENGTH_LONG).show()

                            }) {
                                Text(text = "Add task")
                            }

                        }

                    }
                }
            }
        }

    }

 }



@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    TODOTheme {

    }
}