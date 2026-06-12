package com.andreich.bookreader.ui.booklistscreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andreich.bookreader.R
import com.andreich.bookreader_domain.model.Book

@Composable
fun BookListScreen() {
    val count = 10
    val list = listOf<Book>(
        Book("dd", "book1", "author1", "ddd"),
        Book("dd1", "book1", "author1", "ddd")
    )
    Func {
        LazyColumn(/*state = rememberLazyListState()*/) {
            itemsIndexed(items = list, key = { _: Int, item: Book -> item.id } /*{ list.get(index = it).id}*/) { index: Int, item: Book ->
                Row(modifier = Modifier.fillMaxWidth().border(2.dp, Color.Black)) {
                    val isClicked = remember { mutableStateOf(false) }
                    Icon(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = null,
                        Modifier.padding(4.dp).size(width = 70.dp, height = 120.dp))
                    Column(modifier = Modifier.size(width = 70.dp, height = 120.dp), verticalArrangement = Arrangement.Center) {
                        Text(text = "Название:", fontSize = 14.sp)
                        Text(text = item.title, fontSize = 14.sp)
                        Text(text = "Автор:", fontSize = 14.sp)
                        Text(text = item.author, fontSize = 14.sp, fontStyle = FontStyle.Italic)
                    }
                    Spacer(Modifier.padding())
                    Button(onClick = {
                        isClicked.value = !isClicked.value
                    }, modifier = Modifier.padding()) {
                        Text(if(isClicked.value) "ClickedButton" else "Button")
                    }

                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Func(inside: @Composable ((PaddingValues) -> Unit)) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("TopAppBar")
        }, navigationIcon = {
            IconButton(onClick = {}) {
                Icon(painterResource(R.drawable.menu_24dp), contentDescription = null)
            }
        }, actions = {
            IconButton(onClick = {}) {
                Icon(painterResource(R.drawable.home_24dp), contentDescription = null)
            }
            IconButton(onClick = {}) {
                Icon(painterResource(R.drawable.baseline_arrow_back_24), contentDescription = null)
            }
        })
    }, bottomBar = {
    }) { paddingValues ->
        inside(paddingValues)
    }
}
