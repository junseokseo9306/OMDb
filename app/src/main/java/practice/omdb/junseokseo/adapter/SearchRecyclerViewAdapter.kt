package practice.omdb.junseokseo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import practice.omdb.junseokseo.databinding.ItemSearchOmdbBinding
import practice.omdb.junseokseo.ui.model.MovieUiModel
import practice.omdb.junseokseo.ui.model.UiTransaction
import practice.omdb.junseokseo.utils.Event
import practice.omdb.junseokseo.viewholder.SearchViewHolder

class SearchRecyclerViewAdapter(
    private val transaction: MutableLiveData<Event<UiTransaction>>,
) : ListAdapter<MovieUiModel, SearchViewHolder>(SearchDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchOmdbBinding.inflate(layoutInflater, parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.transaction = transaction
    }
}

private object SearchDiffUtil : DiffUtil.ItemCallback<MovieUiModel>() {
    override fun areItemsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel) =
        oldItem == newItem
}