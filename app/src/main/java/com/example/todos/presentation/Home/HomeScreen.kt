package com.example.todos.presentation.Home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.todos.R
import com.example.todos.dataLayer.localDatabase.TodoEntity
import com.example.todos.presentation.Route
import com.example.todos.ui.theme.chooseRandomColor
import kotlin.random.Random


@SuppressLint("StateFlowValueCalledInComposition", "SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), navController: NavController) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val myColor = MaterialTheme.colorScheme.primaryContainer

        HomeTopAppBar(scrollBehavior, showlist = state.tasks,
            onDelete = {viewModel.onEvent(HomeEvent.DeleteTask(it))},
            navController = navController,
            color = myColor)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    showlist: List<TodoEntity>,
    onDelete: (todoEntity : TodoEntity) -> Unit,
    navController: NavController,
    color: Color
) {

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text(
                            "Todo App",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    scrollBehavior = scrollBehavior,
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.navigate("${Route.Detail.route}/${"T"}/${"T"}/${false}/${0}") }
                ) { Icon(Icons.Filled.Add, "Floating action button.")
                }
            },
            floatingActionButtonPosition = FabPosition.End

        ) { innerPadding ->
            LazyColumn(contentPadding = innerPadding){
                items(showlist){task ->
                    TodoItem(task = task,
                        onDelete = {onDelete(task)},
                        color = color,
                        navigate = {navController.navigate("${Route.Detail.route}/${task.text}/${task.description}/${true}/${task.uid}")} )

                }
            }

        }
}



@SuppressLint("RememberReturnType")
@Composable
fun TodoItem(modifier: Modifier = Modifier, task : TodoEntity, onDelete : () -> Unit,color: Color, navigate : ()-> Unit) {


    ElevatedCard(shape = RoundedCornerShape(16.dp) , modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .clickable { navigate() }
        .padding(5.dp)) {
        Row(modifier = Modifier.background(color = color)) {

            Column(modifier = Modifier
                .weight(5f)
                .wrapContentHeight()
            ) {
                TextForItem(
                    text = task.text,
                    fontSize = 24.sp,
                    modifier = modifier,
                    maxLines = 1
                )

                TextForItem(
                    fontSize = 18.sp,
                    text = task.description,
                    modifier = modifier,
                    maxLines = 10
                )

            }
           Column( modifier = Modifier
               .weight(1f)
               .fillMaxWidth()
               .height(100.dp), verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally) {
               IconButton(onClick = {onDelete()}, enabled = true) {
                   Icon(imageVector = ImageVector.vectorResource(R.drawable.baseline_delete_24),
                       contentDescription = "deleteButton",)
               }
           }
        }
    }
}

@Composable
fun TextForItem(modifier: Modifier = Modifier, fontSize: TextUnit, text: String, maxLines : Int){
    Text(
        text = text,
        fontSize = fontSize,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        color = Color.Black, modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)

    )

}

@Composable
fun TodoDialog(
    title : String,
    onValueChange : (String) -> Unit,
    description :String,
    onValueDesChange : (String) -> Unit,
    onDismissRequest: () -> Unit,
    saveTask : () -> Unit,
    ) {
    Dialog(onDismissRequest = {onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            TextField(value =title, onValueChange ={ onValueChange(it) } ,
                label = { Text(text = "Title")},
                singleLine = true)

            TextField(value = description, onValueChange = {onValueDesChange(it)},
                label = { Text(text = "Description")}, modifier = Modifier
                    .wrapContentHeight()
                    .padding(bottom = 45.dp))
        }
        Row(horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.Bottom) {
            TextButton(onClick = {saveTask() }, modifier = Modifier.offset(x = 115.dp,y = 135.dp)) {
                Text(text = "Save")

            }
            TextButton(onClick = {onDismissRequest() }, modifier = Modifier.offset(x = 135.dp,y= 135.dp)) {
                Text(text = "Cancel")
            }
        }
    }
}

//
//@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun ShowPreview(){
//
//   HomeTopAppBar(scrollBehavior =TopAppBarDefaults.pinnedScrollBehavior(), showlist = listOf(), onDelete ={} ) {
//
//   }
//
//}