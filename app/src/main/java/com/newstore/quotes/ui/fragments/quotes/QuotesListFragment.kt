package com.newstore.quotes.ui.fragments.quotes

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.newstore.quotes.R
import com.newstore.quotes.databinding.FragmentQuotesListBinding
import com.newstore.quotes.di.ViewModelFactory
import com.newstore.quotes.domain.models.QuoteData
import com.newstore.quotes.domain.models.UiState
import com.newstore.quotes.ui.adapters.QuotesAdapter
import com.newstore.quotes.ui.viewmodels.QuotesListViewModel
import com.newstore.quotes.utils.*
import com.newstore.quotes.utils.AppUtils.QuoteActions
import dagger.android.support.DaggerFragment
import java.lang.ref.WeakReference
import javax.inject.Inject

class QuotesListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: QuotesListViewModel by viewModels({
        this
    }, { viewModelFactory })

    private lateinit var binding: FragmentQuotesListBinding
    private var scrollListener: EndlessRecyclerOnScrollListener? = null
    private val quotesAdapter by lazy { QuotesAdapter(::onClickItem) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuotesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchFreshData()
        setUpViews()
        observeViewModel()
    }

    private fun setUpViews() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvQuotes.layoutManager = layoutManager
        binding.rvQuotes.adapter = quotesAdapter
        scrollListener = object : EndlessRecyclerOnScrollListener(layoutManager) {
            override fun onLoadMore(current_page: Int) {
                viewModel.fetchQuotes(isInitialLoad = false)
            }
        }
        resetScrollListenerValues()
        binding.rvQuotes.addOnScrollListener(scrollListener!!)

        addSearchListener()
    }

    private fun addSearchListener() {
        binding.searchLayout.apply {
            etSearchBar.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if ((s?.trim()?.length ?: 0) > 0) {
                        viewModel.fetchQuotes(searchWord = s.toString())
                        ivCross.show()
                    } else {
                        fetchFreshData()
                        ivCross.hide()
                    }
                }
                override fun afterTextChanged(p0: Editable?) {}

            })

            ivCross.setOnClickListener {
                etSearchBar.text?.clear()
                fetchFreshData()
                ivCross.hide()
            }
        }
    }

    private fun fetchFreshData() {
        viewModel.fetchQuotes()
    }

    private fun resetScrollListenerValues() {
        scrollListener?.resetRecylerView()
    }

    private fun observeViewModel() {
        viewModel.apply {
            quotesData.observe(viewLifecycleOwner) {
                quotesAdapter.submitData(it.quotes, it.pageNo)
                if (it.pageNo == 1) {
                    resetScrollListenerValues()
                    if (it.quotes.isEmpty()) {
                        showErrorView(requireContext().getStringResource(R.string.no_data))
                        hideContentRv()
                    } else {
                        showContent()
                    }
                } else {
                    showContent()
                }
            }
            uiViewState.observe(viewLifecycleOwner) {
                handleUiState(it)
            }
        }
    }

    private fun handleUiState(uiState: UiState) {
        when (uiState) {
            is UiState.Loading -> {
                showLoading(uiState.isInitialLoad)
            }
            is UiState.Error -> {
                hideLoading()
                if (viewModel.inputParams.pageNo == 1) {
                    showErrorView()
                } else {
                    requireContext().showToast(requireContext().getStringResource(R.string.network_error))
                }
            }
            is UiState.Success -> {
                hideLoading()
            }
        }
    }

    private fun showContent() {
        binding.rvQuotes.show()
        binding.searchLayout.searchParent.show()
    }

    private fun hideContentRv() {
        binding.rvQuotes.remove()
    }

    private fun showLoading(initialLoad: Boolean) {
        if (initialLoad) {
            hideContentRv()
            binding.loadingProgressBar.show()
        } else {
            binding.loadingPagination.show()
        }
    }

    private fun hideLoading() {
        binding.loadingProgressBar.remove()
        binding.loadingPagination.remove()
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

    fun onClickItem(quoteAction: QuoteActions, quoteData: QuoteData) {
        when (quoteAction) {
            QuoteActions.ITEM_CLICK -> {
                navigateToQuoteDetails(quoteData)
            }
            QuoteActions.SHARE_CLICK -> {
                AppUtils.shareQuote(
                    quoteData.quote.orEmpty(),
                    WeakReference(requireActivity())
                )
            }
            QuoteActions.COPY_CLICK -> {
                requireContext().copyText(getString(R.string.copied), quoteData.quote.orEmpty())
                requireContext().showToast(getString(R.string.copied))
            }
        }
    }

    private fun navigateToQuoteDetails(quoteData: QuoteData) {
        quoteData.quoteId?.let {
            (requireActivity() as? AppCompatActivity)?.addFragment(
                QuoteDetailsFragment.newInstance(it),
                QuoteDetailsFragment::class.java.name,
                R.id.fragment_container_view
            )
        }
    }
}

