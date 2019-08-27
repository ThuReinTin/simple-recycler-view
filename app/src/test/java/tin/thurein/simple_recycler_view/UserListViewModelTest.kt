package tin.thurein.simple_recycler_view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import tin.thurein.simple_recycler_view.viewModels.UserListViewModel

class UserListViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var userListViewModel: UserListViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        userListViewModel = UserListViewModel()
    }

    @Test
    @Throws(Exception::class)
    fun testUserListViewModel() {
        assertNotNull(userListViewModel.mAdapter)
        assertNotNull(userListViewModel.userList)

        userListViewModel.syncUserList()

        assertThat(10, equalTo(userListViewModel.userList.size))
    }

}