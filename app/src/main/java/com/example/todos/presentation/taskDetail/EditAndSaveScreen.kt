package com.example.todos.presentation.taskDetail

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAndSaveScreen(
    viewModel: EditAndSaveViewModel = hiltViewModel(),
    onNavigateToHomeScreen: () -> Unit
){


    val titleState = viewModel.title.value
    val desc = viewModel.desc.value
    val flag = viewModel.flag
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val barString = if (flag== false){
        "Add The Task"
    }else{
        "Edit The Task"
    }

    HomeTopAppBar(
        scrollBehavior = scrollBehavior,
        onClick = { viewModel.onEvent(EditAndSaveEvents.SaveTask) },
        titleText = titleState.text,
        onTitleValueChange = {viewModel.onEvent(EditAndSaveEvents.EnteredTask(it))},
        descriptionText = desc.text,
        onDescriptionValueChange = {viewModel.onEvent(EditAndSaveEvents.EnteredDes(it))},
        titlehint = "Title",
        descHint = "Description",
        topBarString = barString,
        onNavigateToHomeScreen

    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onClick: () -> Unit,
    titleText : String,
    onTitleValueChange : (String) -> Unit,
    descriptionText : String,
    onDescriptionValueChange : (String) -> Unit,
    titlehint: String,
    descHint : String,
    topBarString : String,
    onNavigationToHomeScreen :()-> Unit

) {

    val context = LocalContext.current

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
                        topBarString,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (titleText.isNotEmpty() && descriptionText.isNotEmpty()) {
                        onClick()


                    }
                    onNavigationToHomeScreen()

                }
            ) {
                Icon(Icons.Filled.Done, "Floating action button.")


            }

        },
        floatingActionButtonPosition = FabPosition.End

    ) { innerPadding ->

        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth()
            .wrapContentHeight()) {
            BasicTextFieldUse( text = titleText, onValueChange = {onTitleValueChange(it)}, hint = titlehint, fontSize = 30.sp )
            
            Spacer(modifier = Modifier.height(15.dp))
            BasicTextFieldUse(
                text = descriptionText,
                onValueChange = { onDescriptionValueChange(it) },
                hint = descHint,
                fontSize = 24.sp,

            )
        }


    }
}


@Composable
fun BasicTextFieldUse(text : String, onValueChange : (String) -> Unit, hint : String, fontSize: TextUnit ){

    Box{
        BasicTextField(value = text, onValueChange = {onValueChange(it)}, textStyle = TextStyle(fontSize = fontSize),
            modifier = Modifier.padding(5.dp))
            if(text.isEmpty() ){
                Text(text = hint, color = Color.Black,fontSize = fontSize)
            }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewEditScreen(){


}