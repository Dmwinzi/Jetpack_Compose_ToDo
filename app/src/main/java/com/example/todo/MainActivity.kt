package com.example.todo

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.todo.data.TaskviewModel
import com.example.todo.ui.theme.TODOTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this).get(TaskviewModel::class.java)

        setContent {
            TODOTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val tasklist = viewModel.fetchalltasks().observeAsState(arrayListOf())

                    Scaffold(
                        topBar = { TopAppBar(title = { Text(text = "TO-DO")})},
                        floatingActionButton = { FloatingActionButton(onClick = {
                            val intent  = Intent(this,SecondActivity::class.java)
                            startActivity(intent)
                        }) {
                            Icon(painter = painterResource(id = R.drawable.ic_baseline_add_24), contentDescription =null )
                        }},

                        content = {
                             LazyColumn( content = {
                                 items(items = tasklist.value, itemContent = {
                                     Card(
                                         modifier = Modifier
                                             .background(Color.White)
                                             .padding(8.dp, 4.dp)
                                             .fillMaxWidth()
                                             .height(110.dp)
                                         , shape = RoundedCornerShape(8.dp), elevation = 4.dp
                                     ) {
                                         Text(text = it.title, modifier = Modifier.padding(top = 20.dp, start = 20.dp))
                                         Text(text = it.task,modifier = Modifier.padding(top = 50.dp, start = 20.dp))
                                         Row(horizontalArrangement = Arrangement.End , verticalAlignment = Alignment.Bottom, modifier = Modifier.padding(bottom = 10.dp, start = 20.dp)) {
                                             Icon(painter = painterResource(id = R.drawable.ic_baseline_delete_forever_24), contentDescription = null,
                                                 modifier = Modifier
                                                     .clickable {
                                                         viewModel.deletetask(it.id)
                                                     }
                                                     .size(width = 30.dp, height = 30.dp),
                                                 tint = Color.Red
                                             )
                                         }

                                     }
                                 })
                             })
                        }

                    )


                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TODOTheme {
    }
}