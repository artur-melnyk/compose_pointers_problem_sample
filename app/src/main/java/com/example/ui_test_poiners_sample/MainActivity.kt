package com.example.ui_test_poiners_sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.ui_test_poiners_sample.MainActivity.Companion.TEST_TAG
import com.example.ui_test_poiners_sample.reorderable.ReorderableItem
import com.example.ui_test_poiners_sample.reorderable.detectReorder
import com.example.ui_test_poiners_sample.reorderable.rememberReorderableLazyListState
import com.example.ui_test_poiners_sample.reorderable.reorderable
import com.example.ui_test_poiners_sample.ui.theme.Ui_test_poiners_sampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ui_test_poiners_sampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    VerticalReorderList()
                }
            }
        }
    }

    companion object {
        const val TEST_TAG = "test_tag"
    }
}

@Composable
fun VerticalReorderList() {
    val items = remember { mutableStateOf(List(100) { it }) }
    val state = rememberReorderableLazyListState(onMove = { from, to ->
        items.value = items.value.toMutableList().apply {
            add(to.index, removeAt(from.index))
        }
    })
    Box {
        LazyColumn(
            state = state.listState,
            modifier = Modifier.reorderable(state)
        ) {
            items(items.value, { it }) { item ->
                ReorderableItem(state, orientationLocked = false, key = item) { isDragging ->
                    val elevation = animateDpAsState(if (isDragging) 8.dp else 0.dp)
                    Column(
                        modifier = Modifier
                            .shadow(elevation.value)
                            .background(MaterialTheme.colors.surface)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Image(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "",
                                modifier = Modifier
                                    .detectReorder(state)
                                    .testTag(TEST_TAG)
                            )
                            Text(
                                text = item.toString(),
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                        Divider()
                    }
                }
            }
        }
    }
}