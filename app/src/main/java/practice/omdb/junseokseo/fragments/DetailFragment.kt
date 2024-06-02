package practice.omdb.junseokseo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import practice.omdb.junseokseo.R
import practice.omdb.junseokseo.adapter.RatingRecyclerViewAdapter
import practice.omdb.junseokseo.databinding.FragmentDetailBinding

@AndroidEntryPoint
class DetailFragment : BaseFragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val adapter: RatingRecyclerViewAdapter by lazy {
        RatingRecyclerViewAdapter()
    }

    companion object {
        private const val ARG_IMDB_ID = "imdb_id"
        fun newInstance(id: String) = DetailFragment().apply {
            arguments = bundleOf(
                ARG_IMDB_ID to id
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getString(ARG_IMDB_ID) ?: return
        setupUi(id = id)
        setupObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupUi(id: String) {
        binding.ratingRecyclerview.adapter = adapter
        viewModel.getDetailInfo(id)

        val activity = activity ?: return
        binding.backButton.setOnClickListener {
            activity.onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupObservers() {
        viewModel.detailUiModel.observe(viewLifecycleOwner) { movieDetailUi ->
            adapter.submitList(movieDetailUi.ratings)
            with(binding) {
                posterBigImageView.load(movieDetailUi.poster)
                titleTextView.text = movieDetailUi.title
                metascoreTextView.text = "MetaScore: ${movieDetailUi.metaScore}"
                imdbratingTextView.text = movieDetailUi.imdb
                genreTextView.text = movieDetailUi.genre
                releasedTextView.text = movieDetailUi.releasedDate
                plotTextView.text = movieDetailUi.plot
                actorsTextView.text = movieDetailUi.actors
                nominatedTextView.text = movieDetailUi.awards
                boxofficeTextView.text = movieDetailUi.boxOffice
            }
        }
    }
}