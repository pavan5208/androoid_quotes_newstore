package com.newstore.quotes.ui.fragments.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.newstore.quotes.R
import com.newstore.quotes.di.ViewModelFactory
import com.newstore.quotes.domain.models.UiState
import com.newstore.quotes.ui.viewmodels.LoginSignUpViewModel
import com.newstore.quotes.utils.*
import dagger.android.support.DaggerFragment
import java.util.regex.Pattern
import javax.inject.Inject

abstract class LoginSignUpBaseFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val viewModel: LoginSignUpViewModel by viewModels({
        this
    }, { viewModelFactory })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        observeViewModel()
    }

    abstract fun setUpViews()

    private fun observeViewModel() {
        viewModel.apply {
            uiViewState.observe(viewLifecycleOwner) {
                handleUiState(it)
            }
        }
    }

    private fun handleUiState(uiState: UiState) {
        when (uiState) {
            is UiState.Loading -> {
                showOrHideLoading(true)
            }
            is UiState.Error -> {
                showOrHideLoading(false)
                requireContext().showDialog(AppUtils.getErrorMsg(requireContext(),uiState.errorMsg))
            }
            is UiState.Success -> {
                showSuccessMsg()
                showOrHideLoading(false)
            }
        }
    }

    open fun showSuccessMsg(){
        requireActivity().finish()
    }

    abstract fun showOrHideLoading(showLoader: Boolean)

    fun validations(
        isLogin: Boolean, username: String?, password: String?,
        email: String? = null
    ): Boolean {
        return if (username.isNullOrBlank()) {
            requireContext().showToast(getString(R.string.username_validation))
            false
        } else if (!isLogin && !EMAIL_ADDRESS_PATTERN.matcher(email.toString()).matches()) {
            requireContext().showToast(getString(R.string.email_validation))
            false
        } else if (password.isNullOrBlank()) {
            requireContext().showToast(getString(R.string.password_validation))
            false
        } else if (!isLogin && password.length < 5) {
            requireContext().showToast(getString(R.string.password_length_validation))
            false
        } else if (!requireContext().isNetworkAvailable()) {
            requireContext().showToast(getString(R.string.network_error))
            false
        } else {
            true
        }
    }

    val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
}