package com.designmaster.sukar.fragments


import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.designmaster.sukar.R
import com.designmaster.sukar.activities.HomeActivity
import com.designmaster.sukar.activities.MainActivity
import com.designmaster.sukar.adapters.ChapterAdapter
import com.designmaster.sukar.util.BaseFragment
import java.util.ArrayList


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WhishlistFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var catagwiseproadapter: ChapterAdapter? = null
    lateinit var catagwiseprorlv: RecyclerView
    val chaptersList: ArrayList<String> = ArrayList()
    lateinit var prorl:RelativeLayout
    lateinit var shoprl:RelativeLayout
    lateinit var lineshop: TextView
    lateinit var linepro: TextView

    lateinit var shoptv: TextView
    lateinit var protv: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as MainActivity?)!!.setHeaders(requireActivity().resources.getString(R.string.wishlist),true)

        // Inflate the layout for this fragment
     /*   val view = inflater.inflate(
            R.layout.whishlist,
            container, false
        )*/
        val view = if (OpenastroreFragment.comingfromopenstore==true){
            inflater.inflate(R.layout.whishlistopenstore, container, false)
        } else {
            inflater.inflate(R.layout.whishlist, container, false)
        }
        findviewbyid(view)
        return view
    }

    private fun findviewbyid(view: View) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        val mainIntent = Intent(context, HomeActivity::class.java)
                        startActivity(mainIntent)
                        return true
                    }
                }
                return false
            }
        })
        shopsll = view.findViewById(R.id.shopsll) as LinearLayout

        relativerl1 = view.findViewById(R.id.productsrl) as RelativeLayout
        catagwiseprorlv = view.findViewById(R.id.catagwiseinforlview) as RecyclerView
        shoprl = view.findViewById(R.id.shoprl) as RelativeLayout
        prorl =view.findViewById(R.id.prorl) as RelativeLayout
        lineshop =view.findViewById(R.id.lineshop) as TextView
        linepro =view.findViewById(R.id.linepro) as TextView

        shoptv =view.findViewById(R.id.shoptv) as TextView
        protv =view.findViewById(R.id.producttv) as TextView

        shoprl.setOnClickListener{
            shoptv.setTextColor(resources.getColor(R.color.black))
            protv.setTextColor(resources.getColor(R.color.sand))
            shopsll.visibility = View.VISIBLE
            relativerl1.visibility = View.GONE

            lineshop.visibility=View.VISIBLE
            linepro.visibility=View.GONE
        }
        prorl.setOnClickListener{
            shoptv.setTextColor(resources.getColor(R.color.sand))
            protv.setTextColor(resources.getColor(R.color.black))
            shopsll.visibility = View.GONE
            relativerl1.visibility = View.VISIBLE
            linepro.visibility=View.VISIBLE
            lineshop.visibility=View.GONE
        }

        catagwiseprorlv.setLayoutManager(
            LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )
        chaptersList.add("SHOPS")
        chaptersList.add("PRODUCTS")
        comingfromwishcopy=true
        catagwiseproadapter = ChapterAdapter(activity as FragmentActivity, chaptersList)
        catagwiseprorlv.adapter = catagwiseproadapter
    }


    companion object {
        lateinit var relativerl:RelativeLayout
        lateinit var relativerl1:RelativeLayout
        lateinit var shopsll:LinearLayout
        @kotlin.jvm.JvmField
        var comingfromwishcopy: Boolean = false
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WhishlistFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}