package com.newstore.quotes.ui.fragments.quotes

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.newstore.quotes.R
import com.newstore.quotes.databinding.FragmentRandomQuoteBinding
import com.newstore.quotes.di.ViewModelFactory
import com.newstore.quotes.domain.models.QuoteData
import com.newstore.quotes.domain.models.QuotesPageData
import com.newstore.quotes.domain.models.UiState
import com.newstore.quotes.ui.activities.LoginSignUpActivity
import com.newstore.quotes.ui.viewmodels.QuotesListViewModel
import com.newstore.quotes.utils.*
import dagger.android.support.DaggerFragment
import java.lang.ref.WeakReference
import javax.inject.Inject

class RandomQuoteFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: QuotesListViewModel by viewModels({
        this
    }, { viewModelFactory })
    private lateinit var binding: FragmentRandomQuoteBinding
    private lateinit var randomQuote: QuoteData

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRandomQuoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchFreshData()
        setUpViews()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.apply {
            uiViewState.observe(viewLifecycleOwner) {
                handleUiState(it)
            }
            quotesData.observe(viewLifecycleOwner) {
                if (it.quotes.isEmpty()) {
                    showErrorView(requireContext().getStringResource(R.string.no_data))
                    hideContent()
                } else {
                    showContent(it)
                }
            }
            isUserLoggedInSate.observe(viewLifecycleOwner) {
                if (it.first) {
                    requireContext().showToast(requireContext().getStringResource(R.string.logout_success))
                }
                binding.imAccount.setImageResource(
                    if (it.second) R.drawable.ic_logout
                    else R.drawable.ic_account
                )
            }
        }
    }

    private fun showContent(quotesPageData: QuotesPageData) {
        binding.layoutQuote.apply {
            layoutQuoteParent.show()
            layoutQuoteData.minimumHeight =
                resources.getDimension(R.dimen.dimen_250dp).toInt()
            randomQuote = quotesPageData.quotes[0]
            if (isRandomQuoteInitialised()) {
                txtQuote.text = randomQuote.quote
                txtTags.text =
                    randomQuote.getTagsString().getStringWithAppend(
                        requireContext().getString(R.string.tags), requireContext()
                    )
                txtAuthor.text =
                    randomQuote.quoteAuthor?.getStringWithAppend(
                        requireContext().getString(R.string.author), requireContext()
                    )
            }
        }
    }

    private fun hideContent() {
        binding.layoutQuote.layoutQuoteParent.remove()
    }

    private fun setUpViews() {
        binding.apply {
            btnBrowseQuotes.setOnClickListener {
                (requireActivity() as? AppCompatActivity)?.addFragment(
                    QuotesListFragment(),
                    QuotesListFragment::class.java.name,
                    R.id.fragment_container_view
                )
            }
            layoutQuote.imShare.setOnClickListener {
                AppUtils.shareQuote(
                    layoutQuote.txtQuote.text.toString(),
                    WeakReference(requireActivity())
                )
            }
            layoutQuote.imCopy.setOnClickListener {
                requireContext().copyText(
                    getString(R.string.copied),
                    layoutQuote.txtQuote.text.toString()
                )
                requireContext().showToast(getString(R.string.copied))
            }
            layoutQuote.layoutQuoteParent.setOnClickListener {
                navigateToQuoteDetails()
            }
            imAccount.setOnClickListener {
                if (viewModel.isUserLoggedInSate.value?.second == true) {
                    requireContext().showDialog(requireContext().getStringResource(R.string.logout_alert),
                        {
                            viewModel.logOutUser()
                        }, {})
                } else {
                    LoginSignUpActivity.startActivity(WeakReference(requireActivity()))
                }
            }
        }
    }

    fun isRandomQuoteInitialised() = ::randomQuote.isInitialized

    private fun navigateToQuoteDetails() {
        if (isRandomQuoteInitialised()) {
            randomQuote.quoteId?.let {
                (requireActivity() as? AppCompatActivity)?.addFragment(
                    QuoteDetailsFragment.newInstance(it),
                    QuoteDetailsFragment::class.java.name,
                    R.id.fragment_container_view
                )
            }
        }
    }

    private fun handleUiState(uiState: UiState) {
        when (uiState) {
            is UiState.Loading -> {
                showLoading()
            }
            is UiState.Error -> {
                hideLoading()
                showErrorView()
            }
            is UiState.Success -> {
                hideLoading()
            }
        }
    }

    private fun showLoading() {
        binding.loadingProgressBar.show()
    }

    private fun hideLoading() {
        binding.loadingProgressBar.remove()
    }


    private fun showErrorView(msg: String? = null) {
        binding.emptyLayout.apply {
            tvZeroCase.text = AppUtils.getErrorMsg(requireContext(), msg)
            btnRetry.show()
            retryLayout.show()
            btnRetry.setOnClickListener {
                if (requireContext().isNetworkAvailable()) {
                    retryLayout.remove()
                    btnRetry.remove()
                    fetchFreshData()
                }
            }
        }
    }

    private fun fetchFreshData() {
        viewModel.fetchQuotes()
    }
}