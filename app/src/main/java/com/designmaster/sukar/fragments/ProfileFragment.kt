package com.designmaster.sukar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import com.designmaster.sukar.R
import com.designmaster.sukar.activities.MainActivity
import com.designmaster.sukar.models.MyProfileResponse
import com.designmaster.sukar.models.UpdateProfileResponse
import com.designmaster.sukar.util.*
import com.designmaster.sukar.util.BaseFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : BaseFragment(), View.OnClickListener, ApiCallListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var btnBack: ImageView? = null
    var updaterl: RelativeLayout?=null

    var fnameEdit: EditText? = null
    var lnameEdit: EditText? = null
    var emailEdit: EditText? = null
    var mobileCodeEdit: EditText? = null
    var mobileNoEdit: EditText? = null

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
        // Inflate the layout for this fragment
        val view = inflater.inflate(
            R.layout.fragment_profile,
            container, false

        )
        findviewbyid(view)
        myProfile()
        return view
    }

    private fun findviewbyid(view: View) {
        btnBack = view.findViewById(R.id.btnback)
        btnBack!!.setOnClickListener(this)
        updaterl = view.findViewById(R.id.updaterl)
        updaterl!!.setOnClickListener(this)



        fnameEdit = view.findViewById(R.id.fnameEdit)
        lnameEdit = view.findViewById(R.id.lnameEdit)
        emailEdit = view.findViewById(R.id.emailEdit)
        mobileCodeEdit = view.findViewById(R.id.mobileCodeEdit)
        mobileNoEdit = view.findViewById(R.id.mobileNoEdit)



//        view.setFocusableInTouchMode(true);
//        view.requestFocus();
//        view.setOnKeyListener(object : View.OnKeyListener {
//            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
//                if (event.action == KeyEvent.ACTION_DOWN) {
//                    if (keyCode == KeyEvent.KEYCODE_BACK) {
//                        HomeActivity.botttomappbar.visibility=View.VISIBLE
//                        val fragment = AccountFragment()
//                        (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
//                            R.id.fragment_container,
//                            fragment
//                        ).addToBackStack(null).commit()
//                        return true
//                    }
//                }
//                return false
//            }
//        })
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnback -> {
//                (activity as HomeActivity?)!!.getSupportFragmentManager().popBackStackImmediate()
                (activity as MainActivity?)!!.supportFragmentManager.popBackStackImmediate()
            }
            R.id.updaterl -> {
//                (activity as HomeActivity?)!!.getSupportFragmentManager().popBackStackImmediate()
//                (activity as MainActivity?)!!.supportFragmentManager.popBackStackImmediate()


                if (!isValidPassword(fnameEdit!!.text.toString())) {
                    fnameEdit!!.setError("Enter Firstname")
                }else if (!isValidPassword(lnameEdit!!.text.toString())) {
                    lnameEdit!!.setError("Enter Lastname")
                }else if (!isValidEmail(emailEdit!!.text.toString())) {
                    emailEdit!!.setError("Enter valid Email")
                }else if (!isValidPassword(mobileCodeEdit!!.text.toString())) {
                    mobileCodeEdit!!.setError("Enter Countrycode")
                }else if (!isValidMobile1(mobileNoEdit!!.text.toString())) {
                    mobileNoEdit!!.setError("Enter ContactNumber")
                }else {
                    updateProfile()
                }
            }
        }

    }

    private fun myProfile() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.myProfile(AppPrefs.getUserId(requireActivity()).toString()

            ),
            this,
            ApiConstants.REQUEST_CODE.MY_PROFILE
        )

    }

    private fun updateProfile() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.updateProfile(AppPrefs.getUserId(requireActivity()).toString(),
                fnameEdit!!.text.toString(),
                lnameEdit!!.text.toString(),
                emailEdit!!.text.toString(),
                mobileNoEdit!!.text.toString()

            ),
            this,
            ApiConstants.REQUEST_CODE.UPDATE_PROFILE
        )

    }

    override fun onSuccess(response: String, requestCode: Int) {
        when (requestCode) {
            ApiConstants.REQUEST_CODE.MY_PROFILE ->{
                val apiResponse = RetrofitApiCall.getPayload(
                    MyProfileResponse::class.java,
                    response
                )
                if (apiResponse.output.success == 1) {

                    fnameEdit!!.setText(apiResponse.output.info[0].fname)
                    lnameEdit!!.setText(apiResponse.output.info[0].lname)
                    emailEdit!!.setText(apiResponse.output.info[0].email)
                    mobileCodeEdit!!.setText(apiResponse.output.info[0].countrycode)
                    mobileNoEdit!!.setText(apiResponse.output.info[0].phone)
                }
            }

            ApiConstants.REQUEST_CODE.UPDATE_PROFILE ->{
                val apiResponse = RetrofitApiCall.getPayload(
                    UpdateProfileResponse::class.java,
                    response
                )
                if (apiResponse.output.success == 1) {

                    showToast(apiResponse.output.message)
                }
            }
        }
        hideProgress()
    }

    override fun onError(response: String, requestCode: Int) {
        showToast(response)
        hideProgress()

    }

}