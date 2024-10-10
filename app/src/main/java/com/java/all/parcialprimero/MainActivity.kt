package com.java.all.parcialprimero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.java.all.parcialprimero.ui.theme.ParcialPrimeroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ParcialPrimeroTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FormularioProducto(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun FormularioProducto(modifier: Modifier = Modifier) {
    var nombreProducto by remember { mutableStateOf("") }
    var precioProducto by remember { mutableStateOf("") }
    var categoriaSeleccionada by remember { mutableStateOf("Electrónica") }
    var resultadoFormulario by remember { mutableStateOf("") }

    val resultadoTemplate = stringResource(id = R.string.resultado)

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = nombreProducto,
            onValueChange = { nombreProducto = it },
            label = { Text(stringResource(id = R.string.nombre_producto)) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = precioProducto,
            onValueChange = { precioProducto = it },
            label = { Text(stringResource(id = R.string.precio)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = stringResource(id = R.string.categoria), style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
            RadioButtonWithLabel(
                selected = categoriaSeleccionada == "Electrónica",
                onClick = { categoriaSeleccionada = "Electrónica" },
                label = stringResource(id = R.string.electronica)
            )

            RadioButtonWithLabel(
                selected = categoriaSeleccionada == "Ropa",
                onClick = { categoriaSeleccionada = "Ropa" },
                label = stringResource(id = R.string.ropa)
            )

            RadioButtonWithLabel(
                selected = categoriaSeleccionada == "Alimentos",
                onClick = { categoriaSeleccionada = "Alimentos" },
                label = stringResource(id = R.string.alimentos)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                resultadoFormulario = resultadoTemplate.format(nombreProducto, precioProducto, categoriaSeleccionada)
                // Limpiar el formulario
                nombreProducto = ""
                precioProducto = ""
                categoriaSeleccionada = "Electrónica"
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.enviar))
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (resultadoFormulario.isNotEmpty()) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.medium,
                tonalElevation = 2.dp
            ) {
                Text(
                    text = resultadoFormulario,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun RadioButtonWithLabel(selected: Boolean, onClick: () -> Unit, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 4.dp)) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )
        Text(text = label, modifier = Modifier.padding(start = 8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun FormularioProductoPreview() {
    ParcialPrimeroTheme {
        FormularioProducto()
    }
}
