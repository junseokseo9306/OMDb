package practice.omdb.junseokseo.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class GridDecoration(
    private val spanCount: Int,
    private val spacing: Int
) : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position: Int = parent.getChildAdapterPosition(view)
        val column = position % spanCount
        outRect.apply {
            left = spacing - (column * spacing / spanCount)
            right = (column + 1) * spacing / spanCount
            if (position < spanCount) top = spacing else spacing / 2
            bottom = spacing
        }
    }
}
