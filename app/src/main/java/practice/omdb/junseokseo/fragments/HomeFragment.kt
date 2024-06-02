package practice.omdb.junseokseo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
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
        }
    }

    private fun setupObservers() {
        viewModel.movieList.observe(viewLifecycleOwner) { movieList ->
            adapter.submitList(movieList)
        }
    }
}