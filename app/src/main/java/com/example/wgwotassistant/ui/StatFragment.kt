package com.example.wgwotassistant.ui

import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wgwotassistant.R
import kotlinx.android.synthetic.main.player_stat.*
import com.example.wgwotassistant.ui.SearchFragment.Companion.NICKNAME

class StatFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.player_stat, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainMenu2.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(
                    R.id.container,
                    SearchFragment()
                )
                ?.commit()
        }

        nameText.text = arguments?.getString(NICKNAME)
    }
}