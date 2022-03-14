package com.ahmed.mviapplication.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ahmed.mviapplication.databinding.FragmentLoginBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        lifecycleScope.launchWhenResumed {
            viewModel.store.state.collect {
                processViewState(it)
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            viewModel.signInClicked()
            if (binding.emailEditText.text.length < 5 || binding.emailEditText.text.isNullOrEmpty()) {
                viewModel.invalidEmailSubmitted()
            } else {
                viewModel.loginStarted()
                viewModel.loginCompleted()
            }
        }

        binding.emailEditText.doOnTextChanged { text, start, before, count ->
            viewModel.emailChanged(text.toString())
        }

        binding.passwordEditText.doOnTextChanged { text, start, before, count ->
            viewModel.passwordChanged(text.toString())
        }


    }

    suspend fun processViewState(viewState: LoginViewState) {
        binding.progressBar.visibility = if (viewState.showProgressBar) View.VISIBLE else View.GONE
        delay(500L)
        binding.emailEditText.error = viewState.emailError
    }
}