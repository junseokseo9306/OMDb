package practice.omdb.junseokseo.fragments

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import practice.omdb.junseokseo.MainActivity
import practice.omdb.junseokseo.ui.model.DetailFragmentEvent
import practice.omdb.junseokseo.ui.model.HomeFragmentEvent
import practice.omdb.junseokseo.viewModel.SharedViewModel

@AndroidEntryPoint
open class BaseFragment(@LayoutRes val layoutId: Int) : Fragment(layoutId) {
    protected val viewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBaseObservers()
    }

    private fun setupBaseObservers() {
        val activity = activity ?: return
        val mainActivity = activity as MainActivity
        viewModel.transactionEvent.observe(viewLifecycleOwner) {
            val event = it.getContentIfNotHandled() ?: return@observe
            when (event) {
                is HomeFragmentEvent -> {
                    mainActivity.replaceFragment(
                        fragment = HomeFragment(),
                        backStack = true
                    )
                }

                is DetailFragmentEvent -> {
                    val id = event.id
                    mainActivity.replaceFragment(
                        fragment = DetailFragment.newInstance(id),
                        backStack = true
                    )
                }
            }
        }
    }
}