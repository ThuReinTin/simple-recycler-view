package tin.thurein.simple_recycler_view.viewModels

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import tin.thurein.simple_recycler_view.listeners.OnChildViewClickListener
import tin.thurein.simple_recycler_view.models.User

class UserViewModel(val user: User = User(), val mListener: OnChildViewClickListener): BaseObservable() {

    @Bindable
    fun getName(): String {
        return user.name
    }

    fun setName(name: String) {
        user.name = name
    }

    @Bindable
    fun getAge(): String {
        return user.age.toString()
    }

    @Bindable
    fun getAddress(): String {
        return user.address
    }

    fun setAddress(address: String) {
        user.address = address
    }

    fun onClick(position: Int) {
        mListener.onClick(position)
    }
}