package tin.thurein.simple_recycler_view

import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowToast

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {
    private fun getActivity(): MainActivity {
        val controller = Robolectric.buildActivity(MainActivity::class.java).setup()
        val activity = controller.resume().get()
        return activity
    }

    private fun onItemClick(position: Int, message: String) {
        val activity = getActivity()
        val rvUserList = activity.findViewById<RecyclerView>(R.id.rvUserList)

        Assert.assertNotNull(rvUserList)
        rvUserList.measure(0,0)
        rvUserList.layout(0,0,100,1000)

        // lets just imply a certain item at position 0 for simplicity
        rvUserList.findViewHolderForAdapterPosition(position)?.itemView?.performClick()

        Assert.assertThat(ShadowToast.getTextOfLatestToast(), CoreMatchers.equalTo(message))

    }

    @Test
    fun onItemClickTest() {
        onItemClick(0, "User name : Name 1 \n" +
                " Age : 19 \n" +
                " Address : Address 1")
    }
}