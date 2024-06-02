package practice.omdb.junseokseo.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import practice.omdb.junseokseo.Constants
import practice.omdb.junseokseo.R
import practice.omdb.junseokseo.adapter.SearchRecyclerViewAdapter
import practice.omdb.junseokseo.databinding.FragmentHomeBinding
import practice.omdb.junseokseo.decoration.GridDecoration
import practice.omdb.junseokseo.utils.fromDpToPx

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val adapter: SearchRecyclerViewAdapter by lazy {
        SearchRecyclerViewAdapter(viewModel.transactionEvent)
    }
    private val decoration: GridDecoration by lazy {
        GridDecoration(spanCount = 2, spacing = 8f.fromDpToPx())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupObservers()
        setupClickListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupUi() {
        with(binding) {
            searchRecyclerView.adapter = adapter
            searchRecyclerView.scheduleLayoutAnimation()
            searchRecyclerView.addItemDecoration(decoration)
            searchEditText.setOnEditorActionListener { view, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    viewModel.getSearch()
                    view.clearFocus()
                    hideKeyboard(view)
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }
        }

        binding.searchRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!binding.searchRecyclerView.canScrollVertically(Constants.RECYCLERVIEW_END)) {
                    viewModel.loadMoreData()
                }
            }
        })
    }

    private fun setupObservers() {
        viewModel.movieList.observe(viewLifecycleOwner) { movieList ->
            adapter.submitList(movieList)
        }

        viewModel.errorText.observe(viewLifecycleOwner) { message ->
            val context = context ?: return@observe
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        viewModel.loadingStatus.observe(viewLifecycleOwner) {isLoading ->
            binding.loadingBar.isVisible = isLoading
        }
    }

    private fun setupClickListeners() {
        binding.searchClickImageButton.setOnClickListener {
            viewModel.getSearch()
        }

        binding.searchEditText.addTextChangedListener(
            afterTextChanged = { text ->
                viewModel.changeKeyword(text?.toString())
            }
        )
    }

    private fun hideKeyboard(view: View) {
        val context = context ?: return
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}