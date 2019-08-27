package tin.thurein.simple_recycler_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import tin.thurein.simple_recycler_view.databinding.ActivityMainBinding
import tin.thurein.simple_recycler_view.viewModels.UserListViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var userListViewModel: UserListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        userListViewModel = UserListViewModel()
        binding.userListViewModel = userListViewModel
        userListViewModel.userMutabeResult.observe(this, Observer {
            Toast.makeText(this@MainActivity, "User name : ${it?.name} \n Age : ${it?.age} \n Address : ${it.address}", Toast.LENGTH_SHORT).show()
        })
        userListViewModel.syncUserList()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.unbind()
    }
}
