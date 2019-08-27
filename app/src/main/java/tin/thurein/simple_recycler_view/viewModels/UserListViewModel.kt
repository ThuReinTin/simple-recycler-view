package tin.thurein.simple_recycler_view.viewModels

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import tin.thurein.simple_recycler_view.BR
import tin.thurein.simple_recycler_view.adapters.UserListAdapter
import tin.thurein.simple_recycler_view.listeners.OnChildViewClickListener
import tin.thurein.simple_recycler_view.models.User
import kotlin.collections.ArrayList

class UserListViewModel: BaseObservable(), OnChildViewClickListener {
    val userMutabeResult = MutableLiveData<User>()

    val userList: ArrayList<User>
    var mAdapter: UserListAdapter

    var refresh = false
    var _scrollTo = 0

    init {
        userList = ArrayList()
        mAdapter = UserListAdapter(this)
        mAdapter.userList = userList
    }

    var isRefreshing
    @Bindable
    get() = refresh
    set(value) {
        refresh = value
        notifyPropertyChanged(BR.isRefreshing)
    }

    var scrollToPosition: Int
    @Bindable
    get() = _scrollTo
    set(value) {
        _scrollTo = value
        notifyPropertyChanged(BR.scrollToPosition)
    }
    @Bindable
    fun getIsRefreshing(): Boolean {
        return isRefreshing
    }

    @Bindable
    fun getAdapter(): UserListAdapter {
        return mAdapter
    }

    fun setAdapter(adapter: UserListAdapter) {
        mAdapter.notifyDataSetChanged()
    }

    @Bindable
    fun getOnRefreshListener() : SwipeRefreshLayout.OnRefreshListener {
        return SwipeRefreshLayout.OnRefreshListener {
            userList.clear()
            syncUserList()
        }
    }
    fun syncUserList() {
        isRefreshing = true
        for (i in 1..10) {
            val user = User()
            user.name = "Name $i"
            user.age = 18 + i
            user.address = "Address $i"
            userList.add(user)
        }

        notifyPropertyChanged(BR.adapter)
        scrollToPosition = userList.size - 10
        isRefreshing = false
    }

    override fun onClick(position: Int) {
        userMutabeResult.value = userList[position]
    }

    override fun onLoadMore() {
        syncUserList()
    }
}