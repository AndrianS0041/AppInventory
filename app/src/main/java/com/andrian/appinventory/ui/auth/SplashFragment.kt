package com.andrian.appinventory.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.andrian.appinventory.R
import com.andrian.appinventory.data.repository.AuthRepository
import com.andrian.appinventory.databinding.FragmentSplashBinding
import kotlinx.coroutines.*

class SplashFragment : Fragment() {
    val parent: AuthActivity by lazy { activity as AuthActivity }
    val viewModel: AuthViewModel by lazy { AuthViewModel(AuthRepository(parent)) }
    lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)
            withContext(Dispatchers.Main) {
                if (viewModel.isLogin.value == true){
                    viewModel.authUser.value?.let { parent.onSuccess(it) }
                } else {
                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())

                }
            }
        }
    }

}