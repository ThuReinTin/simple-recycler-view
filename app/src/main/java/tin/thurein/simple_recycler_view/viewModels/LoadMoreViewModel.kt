package tin.thurein.simple_recycler_view.viewModels

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import tin.thurein.simple_recycler_view.listeners.LoadMoreListener

class LoadMoreViewModel(val mListener: LoadMoreListener): BaseObservable() {

    fun onClick() {
        mListener.onLoadMore()
    }
}