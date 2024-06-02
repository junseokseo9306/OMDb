package practice.omdb.junseokseo.viewholder

import androidx.recyclerview.widget.RecyclerView
import practice.omdb.junseokseo.databinding.ItemRatingOmdbBinding
import practice.omdb.junseokseo.ui.model.Rating

class RatingViewHolder(
    private val binding: ItemRatingOmdbBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Rating) {
        with(binding) {
            ratingTitleView.text = item.title
            ratingDetailView.text = item.score
        }
    }
}