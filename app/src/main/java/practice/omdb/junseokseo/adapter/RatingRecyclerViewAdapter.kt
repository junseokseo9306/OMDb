package practice.omdb.junseokseo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import practice.omdb.junseokseo.databinding.ItemRatingOmdbBinding
import practice.omdb.junseokseo.ui.model.Rating
import practice.omdb.junseokseo.viewholder.RatingViewHolder

class RatingRecyclerViewAdapter : ListAdapter<Rating, RatingViewHolder>(RatingDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRatingOmdbBinding.inflate(layoutInflater, parent, false)
        return RatingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

private object RatingDiffUtil : DiffUtil.ItemCallback<Rating>() {
    override fun areItemsTheSame(oldItem: Rating, newItem: Rating) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Rating, newItem: Rating) = oldItem == newItem
}