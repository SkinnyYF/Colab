package com.example.android.wearable.composeforwearos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.material.Checkbox
import androidx.wear.compose.material.CheckboxDefaults
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Switch
import com.example.android.wearable.composeforwearos.theme.WearAppTheme
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.layout.AppScaffold
import com.google.android.horologist.compose.layout.ScalingLazyColumn
import com.google.android.horologist.compose.layout.ScalingLazyColumnDefaults
import com.google.android.horologist.compose.layout.ScalingLazyColumnDefaults.ItemType
import com.google.android.horologist.compose.layout.ScreenScaffold
import com.google.android.horologist.compose.layout.rememberResponsiveColumnState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WearApp()
        }
    }
}

@OptIn(ExperimentalHorologistApi::class)
@Composable
fun WearApp() {
    WearAppTheme {
        AppScaffold {
            val listState = rememberResponsiveColumnState(
                contentPadding = ScalingLazyColumnDefaults.padding(
                    first = ItemType.SingleButton,
                    last = ItemType.Chip,
                ),
            )
            val contentModifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            val iconModifier = Modifier.size(24.dp).wrapContentSize(align = Alignment.Center)

            ScreenScaffold(
                scrollState = listState,
            ) {
                ScalingLazyColumn(
                    columnState = listState,
                ) {
                    // Composables existentes
                    item { ButtonExample(contentModifier, iconModifier) }
                    item { TextExample(contentModifier) }
                    item { CardExample(contentModifier, iconModifier) }
                    item { ChipExample(contentModifier, iconModifier) }
                    item { ToggleChipExample(contentModifier) }

                    // Componente adicional 1: CircularProgressIndicator
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(48.dp)
                                .wrapContentSize(Alignment.Center)

                        )
                    }


                    // Componente adicional 2: Checkbox
                    item {
                        val checkedState = remember { mutableStateOf(false) }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = checkedState.value,
                                onCheckedChange = { checkedState.value = it },
                                colors = CheckboxDefaults.colors(
                                    checkedBoxColor = MaterialTheme.colors.primary,
                                    uncheckedBoxColor = Color.Magenta
                                )
                            )
                            Text(
                                text = if (checkedState.value) "Seleccionado" else "No seleccionado",
                                modifier = Modifier.padding(start = 8.dp)


                            )

                        }
                        // Componente adicional 3: LazyRow
                        this@ScalingLazyColumn.item {
                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            ) {
                                items(7) { index ->
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier.padding(horizontal = 8.dp)
                                    ) {
                                        // Aquí puedes usar Icon o Text
                                        Text(
                                            text = "Hola $index",
                                            modifier = Modifier.padding(8.dp)
                                        )
                                        // Descomenta y reemplaza con un ícono válido si lo necesitas
                                        // Icon(
                                        //     painter = painterResource(id = R.drawable.ic_launcher_foreground),
                                        //     contentDescription = "Ícono $index",
                                        //     modifier = Modifier.size(24.dp)
                                        // )
                                    }
                                }
                            }
                        }
                        }
                    }
                }
            }
        }
    }

