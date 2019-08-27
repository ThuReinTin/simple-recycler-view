package tin.thurein.simple_recycler_view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

import tin.thurein.simple_recycler_view.R
import tin.thurein.simple_recycler_view.databinding.LoadMoreLayoutBinding
import tin.thurein.simple_recycler_view.databinding.UserLayoutBinding
import tin.thurein.simple_recycler_view.listeners.OnChildViewClickListener
import tin.thurein.simple_recycler_view.models.User
import tin.thurein.simple_recycler_view.viewModels.LoadMoreViewModel
import tin.thurein.simple_recycler_view.viewModels.UserViewModel

class UserListAdapter(val mListener: OnChildViewClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var userList: ArrayList<User>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater: LayoutInflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        if (viewType == 2) {
            val binding: LoadMoreLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.load_more_layout, parent, false)
            val mHolder = LoadMoreViewHolder(binding)
            return mHolder
        }
        val binding: UserLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.user_layout, parent, false)
        val mHolder = UserViewHolder(binding)
        return mHolder
    }

    override fun getItemCount(): Int {
        return userList.size + 1
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        try {
            val mHolder = holder as UserViewHolder
            mHolder.unbind()
        } catch (ex: Exception) {
            val mHolder = holder as LoadMoreViewHolder
            mHolder.unbind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == userList.size) 2 else 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position < userList.size) {
            val mHolder = holder as UserViewHolder
            val userViewModel = UserViewModel(userList[position], mListener)
            mHolder.bind(userViewModel, position)
        } else {
            val mHolder = holder as LoadMoreViewHolder
            val loadMoreViewModel = LoadMoreViewModel(mListener)
            mHolder.bind(loadMoreViewModel, if (position == 40) View.GONE else View.VISIBLE)
        }
    }

    class UserViewHolder(binding: UserLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding: UserLayoutBinding
        init {
            this.binding = binding
        }

        fun bind(userViewModel: UserViewModel, position: Int) {
            binding.userViewModel = userViewModel
            binding.position = position
        }

        fun unbind() {
            binding.unbind()
        }
    }

    class LoadMoreViewHolder(binding: LoadMoreLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val binding: LoadMoreLayoutBinding
        init {
            this.binding = binding
        }

        fun bind(loadMoreViewModel: LoadMoreViewModel, visibility: Int) {
            binding.loadMoreViewModel = loadMoreViewModel
            binding.visibility = visibility
        }

        fun unbind() {
            binding.unbind()
        }
    }
}