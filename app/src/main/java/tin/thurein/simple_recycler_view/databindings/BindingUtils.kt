package tin.thurein.simple_recycler_view.databindings

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class BindingUtils {
    companion object {
        @BindingAdapter(value = ["app:onRefreshListener", "app:isRefreshing"], requireAll = false)
        @JvmStatic
        fun setOnRefreshListener(srl: SwipeRefreshLayout, listener: SwipeRefreshLayout.OnRefreshListener, isRefreshing: Boolean) {
            srl.setOnRefreshListener(listener)
            srl.isRefreshing = isRefreshing
        }

        @BindingAdapter(value = ["app:adapter", "app:scrollTo"], requireAll = false)
        @JvmStatic
        fun setAdapter(rv: RecyclerView, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>, position: Int) {
            rv.adapter = adapter
            rv.scrollToPosition(position)
        }
    }
}