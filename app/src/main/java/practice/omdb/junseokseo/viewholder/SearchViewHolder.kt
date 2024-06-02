package practice.omdb.junseokseo.viewholder

import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import coil.load
import practice.omdb.junseokseo.databinding.ItemSearchOmdbBinding
import practice.omdb.junseokseo.ui.model.DetailFragmentEvent
import practice.omdb.junseokseo.ui.model.MovieUiModel
import practice.omdb.junseokseo.ui.model.UiTransaction
import practice.omdb.junseokseo.utils.Event

class SearchViewHolder(
    private val binding: ItemSearchOmdbBinding
) : RecyclerView.ViewHolder(binding.root) {
    var transaction: MutableLiveData<Event<UiTransaction>>? = null

    fun bind(item: MovieUiModel) {
        with(binding) {
            itemBackground.clipToOutline = true
            posterImageView.load(item.posterUrl)
            posterTitleView.text = item.title
        }

        binding.itemBackground.setOnClickListener {
            transaction?.value = Event(
                DetailFragmentEvent(
                    item.id
                )
            )
        }
    }
}