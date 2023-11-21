package ar.com.wolftei.cyphermessage.dataClass

data class FormDataClass(
    val message: String,
    val desp: String? = null,
    val cipher: String,
    val onValueChange: (String) -> Unit,
    val onValueChangeDesp: (String) -> Unit? = { null },
    val onCipher: () -> Unit,
    val onDecipher: () -> Unit,
)