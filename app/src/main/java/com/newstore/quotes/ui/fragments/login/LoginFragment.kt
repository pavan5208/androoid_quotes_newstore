package com.newstore.quotes.ui.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.newstore.quotes.R
import com.newstore.quotes.databinding.FragmentLoginBinding
import com.newstore.quotes.utils.addFragment
import com.newstore.quotes.utils.remove
import com.newstore.quotes.utils.show
import com.newstore.quotes.utils.showToast

class LoginFragment : LoginSignUpBaseFragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
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
            txtSignUp.setOnClickListener {
                (requireActivity() as? AppCompatActivity)?.addFragment(
                    SignUpFragment(),
                    SignUpFragment::class.java.name,
                    R.id.fragment_container_view
                )
            }

            btnLogin.setOnClickListener {
                if (validations(
                        true,
                        binding.etUsername.text.toString(),
                        binding.etPassword.text.toString()
                    )
                ) {
                    viewModel.actionLogin(etUsername.text.toString(), etPassword.text.toString())
                }
            }
        }
    }

    override fun showSuccessMsg() {
        super.showSuccessMsg()
        requireContext().showToast(getString(R.string.login_success))
    }

}