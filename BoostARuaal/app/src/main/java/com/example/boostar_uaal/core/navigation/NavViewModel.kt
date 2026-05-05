package com.example.boostar_uaal.core.navigation

import android.util.Log
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID


/**
 * ViewModel auxiliar utilizado como "puente" para transferir datos entre pantallas
 * dentro del sistema de navegación personalizado de la aplicación.
 *
 * CONTEXTO ARQUITECTÓNICO:
 * El enrutador propio de la app no dispone de un mecanismo nativo para devolver resultados
 * a una pantalla anterior al hacer la acción de "Atrás". Este ViewModel soluciona esa limitación
 * actuando como una memoria temporal compartida. Su caso de uso principal (hasta la fecha) es
 * retener el identificador (`feedUuid`) originado en la `ArScreen` para que la `FeedScreen`
 * pueda consumirlo al reanudarse.
 *
 */

class NavViewModel: ViewModel(){
    private var _feedUuid: MutableStateFlow<String?> = MutableStateFlow<String?>(null)
    val feedUuid: StateFlow<String?> = _feedUuid.asStateFlow()

    fun setUuid(newUuid: String?){
        _feedUuid.value = newUuid
    }

    fun clearUuid(){
        _feedUuid.value = null
    }
}
