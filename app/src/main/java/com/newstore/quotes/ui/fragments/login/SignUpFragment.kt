package com.newstore.quotes.ui.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.newstore.quotes.R
import com.newstore.quotes.databinding.FragmentSignUpBinding
import com.newstore.quotes.utils.remove
import com.newstore.quotes.utils.show
import com.newstore.quotes.utils.showToast

class SignUpFragment : LoginSignUpBaseFragment() {

    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    override fun showOrHideLoading(showLoader: Boolean) {
        if (showLoader) {
            binding.layoutLoader.loadingProgressLayout.show()
        } else {
            binding.layoutLoader.loadingProgressLayout.remove()
        }
    }

    override fun setUpViews() {
        binding.apply {
            txtLogin.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStackImmediate()
            }

            btnSignUp.setOnClickListener {
                val username = binding.etUsername.text.toString()
                val password = binding.etPassword.text.toString()
                val email = binding.etEmail.text.toString()

                if (validations(
                        false,
                        username,
                        password,
                        email
                    )
                ) {
                    viewModel.actionSignUp(username, email, password)
                }
            }
        }
    }

    override fun showSuccessMsg() {
        super.showSuccessMsg()
        requireContext().showToast(getString(R.string.register_success))
    }
}
