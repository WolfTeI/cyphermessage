package ar.com.wolftei.cyphermessage.model.formcypher

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.com.wolftei.cyphermessage.dataClass.FormDataClass
import ar.com.wolftei.cyphermessage.model.funtions.clipBoard
import ar.com.wolftei.cyphermessage.model.funtions.shareMessage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormCypherDesp(dataForm: FormDataClass, context: Context, nombre: String, isNumber: Boolean) {
    val focusManager = LocalFocusManager.current
    val type = if(isNumber) KeyboardType.Number else KeyboardType.Text
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 80.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = dataForm.desp?: "",
                onValueChange = { dataForm.onValueChangeDesp(it) },
                label = { Text(text = nombre) },
                modifier = Modifier
                    .width(200.dp)
                    .padding(horizontal = 16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Sentences,
                    autoCorrect = false,
                    keyboardType = type,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )
        }
        OutlinedTextField(
            value = dataForm.message,
            onValueChange = { dataForm.onValueChange(it) },
            label = { Text(text = "Mensaje") },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(horizontal = 16.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences,
                autoCorrect = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )

        Divider(modifier = Modifier.padding(16.dp), color = MaterialTheme.colorScheme.surface)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    if(dataForm.message != ""){
                        dataForm.onCipher()
                    }else{
                        Toast.makeText(context, "No hay mensaje para cifrar", Toast.LENGTH_SHORT).show()
                    }
                }, modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = "cifrar"
                    )
                    Text(text = "Cifrar")
                }
            }

            IconButton(
                onClick = {
                    if(dataForm.message != ""){
                        dataForm.onDecipher()
                    }else{
                        Toast.makeText(context, "No hay mensaje para descifrar", Toast.LENGTH_SHORT).show()
                    }
                }, modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        imageVector = Icons.Filled.LockOpen,
                        contentDescription = "Descifrar",
                    )
                    Text(text = "Descifrar")
                }

            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = dataForm.cipher,
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(horizontal = 16.dp)
            )
        }

        Divider(modifier = Modifier.padding(16.dp), color = MaterialTheme.colorScheme.surface)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    clipBoard(context, dataForm.cipher)
                }, modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        imageVector = Icons.Filled.ContentCopy,
                        contentDescription = "Copiar"
                    )
                    Text(text = "Copiar")
                }
            }

            IconButton(
                onClick = {
                    shareMessage(dataForm.cipher, context)
                }, modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Share,
                        contentDescription = "Compartir",
                    )
                    Text(text = "Compartir")
                }

            }
        }

    }
}
