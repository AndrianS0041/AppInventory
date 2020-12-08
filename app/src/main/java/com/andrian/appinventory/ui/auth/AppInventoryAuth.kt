package com.andrian.appinventory.ui.auth

import android.content.Context
import com.andrian.appinventory.data.model.ActionState
import com.andrian.appinventory.data.repository.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.security.auth.callback.Callback

object AppInventoryAuth {

    fun logout(contex: Context, callback: ((ActionState<Boolean>) -> Unit)? = null) {
        val repo = AuthRepository(contex)
        CoroutineScope(Dispatchers.IO).launch {
            val resp = repo.logout()
            withContext(Dispatchers.Main) {
                if (callback != null) callback.invoke(resp)
            }
        }
    }
}