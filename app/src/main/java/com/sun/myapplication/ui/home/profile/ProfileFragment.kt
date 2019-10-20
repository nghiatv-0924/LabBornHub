package com.sun.myapplication.ui.home.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.sun.myapplication.R
import com.sun.myapplication.base.BaseFragment
import com.sun.myapplication.base.FragmentInteractionListener
import com.sun.myapplication.ui.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_update_profile.*

class ProfileFragment : BaseFragment() {

    private var listener: OnProfileFragmentInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnProfileFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context $ERROR_IMPLEMENT_FRAGMENT_INTERACTION_LISTENER")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_update_profile, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(context, LoginActivity::class.java))
            listener?.signOut()
        }
        listener?.setToolbarTitle("Thông tin cá nhân")
    }

    interface OnProfileFragmentInteractionListener : FragmentInteractionListener {
        fun signOut()
    }

    companion object {
        private const val ERROR_IMPLEMENT_FRAGMENT_INTERACTION_LISTENER =
            "must implement OnKanjiBasicFragmentInteractionListener"

        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}
