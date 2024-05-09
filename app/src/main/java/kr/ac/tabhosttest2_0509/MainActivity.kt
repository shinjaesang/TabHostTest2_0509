package kr.ac.tabhosttest2_0509

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity(), ActionBar.TabListener {
    lateinit var tab1 : ActionBar.Tab
    lateinit var tab2 : ActionBar.Tab
    lateinit var tab3 : ActionBar.Tab
    var arrFrags = arrayOfNulls<MyFragment>(3)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var bar = this.supportActionBar
        bar!!.navigationMode = ActionBar.NAVIGATION_MODE_TABS

        tab1 = bar.newTab()
        tab1.text = "FIRST TAB"
        tab1.setTabListener(this)
        bar.addTab(tab1)

        tab2 = bar.newTab()
        tab2.text = "SECOND TAB"
        tab2.setTabListener(this)
        bar.addTab(tab2)

        tab3 = bar.newTab()
        tab3.text = "THIRD TAB"
        tab3.setTabListener(this)
        bar.addTab(tab3)


    }

    override fun onTabSelected(tab: ActionBar.Tab?, ft: FragmentTransaction?) {
        var fragment: MyFragment? = null

        if (arrFrags[tab!!.position] == null) {
            fragment = MyFragment()
            val data = Bundle()
            data.putString("tabName", tab.text.toString())
            fragment.arguments = data
            arrFrags[tab.position] = fragment
        } else {
            fragment = arrFrags[tab.position]
        }
        ft!!.replace(android.R.id.content, fragment!!)
    }
    override fun onTabUnselected(tab: ActionBar.Tab?, ft: FragmentTransaction?) {
        TODO("Not yet implemented")
    }

    override fun onTabReselected(tab: ActionBar.Tab?, ft: FragmentTransaction?) {
        TODO("Not yet implemented")
    }

    class MyFragment : Fragment(){
        var tabName : String? = null
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            var data = arguments
            tabName  = data!!.getString("tabName")
        }

            override fun onCreateView(
                inflater: LayoutInflater,
                container: ViewGroup?,
                savedInstanceState: Bundle?
            ): View? {

                var params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
                var linear = LinearLayout(super.getActivity())
                linear.orientation = LinearLayout.VERTICAL
                linear.layoutParams = params

                if(tabName === "FIRST TAB")
                    linear.setBackgroundColor(Color.RED)
                if(tabName === "SECOND TAB")
                    linear.setBackgroundColor(Color.YELLOW)
                if(tabName === "THIRD TAB")
                    linear.setBackgroundColor(Color.GREEN)
                return linear


                return super.onCreateView(inflater, container, savedInstanceState)
            }
        }

    }
