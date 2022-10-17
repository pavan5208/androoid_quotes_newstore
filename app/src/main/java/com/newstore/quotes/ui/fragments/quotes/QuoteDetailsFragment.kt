package com.newstore.quotes.ui.fragments.quotes

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.newstore.quotes.databinding.FragmentQuoteDetailsBinding
import com.newstore.quotes.di.ViewModelFactory
import com.newstore.quotes.domain.models.QuoteData
import com.newstore.quotes.domain.models.UiState
import com.newstore.quotes.ui.viewmodels.QuoteDetailsViewModel
import com.newstore.quotes.utils.AppUtils
import com.newstore.quotes.utils.isNetworkAvailable
import com.newstore.quotes.utils.remove
import com.newstore.quotes.utils.show
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class QuoteDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: QuoteDetailsViewModel by viewModels({
        this
    }, { viewModelFactory })

    private lateinit var binding: FragmentQuoteDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuoteDetailsBinding.inflate(inflater, container, false)
        viewModel.setQuoteId(arguments?.getInt(QUOTE_ID) ?: 0)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchQuoteDetails()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.apply {
            uiViewState.observe(viewLifecycleOwner) {
                handleUiState(it)
            }
            quotesData.observe(viewLifecycleOwner) {
                setData(it)
            }
        }
    }

    private fun setData(quote: QuoteData) {
        binding.txtQuoteDetails.apply {
            text = AppUtils.getQuoteDetailString(quote, requireContext())
            movementMethod = ScrollingMovementMethod()
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
        binding.pbLoading.show()
    }

    private fun hideLoading() {
        binding.pbLoading.remove()
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
                    fetchQuoteDetails()
                }
            }
        }
    }

    private fun fetchQuoteDetails() {
        viewModel.fetchQuoteDetails()
    }

    companion object {
        const val QUOTE_ID = "quoteId"
        fun newInstance(id: Int): QuoteDetailsFragment {
            val bundle = Bundle()
            bundle.putInt(QUOTE_ID, id)
            val fragment = QuoteDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}