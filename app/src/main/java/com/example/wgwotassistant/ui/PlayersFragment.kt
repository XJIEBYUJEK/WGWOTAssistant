package com.example.wgwotassistant.ui

import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wgwotassistant.R
import com.example.wgwotassistant.ui.SearchFragment.Companion.ID
import com.example.wgwotassistant.ui.SearchFragment.Companion.NICKNAME
import kotlinx.android.synthetic.main.fragment_players.*

class PlayersFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_players, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainMenu.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(
                    R.id.container,
                    SearchFragment()
                )
                ?.commit()
        }

        idText.text = arguments?.getString(ID)
        val nickname = arguments?.getString(NICKNAME)
        playerButton.text = nickname

        playerButton.setOnClickListener {
            val bundle = Bundle()
            val fragment = StatFragment()
            bundle.putString(NICKNAME, nickname)
            fragment.arguments = bundle

            fragmentManager?.beginTransaction()
                ?.replace(R.id.container,fragment)      //переход на новый экран
                ?.commit()
        }

    }

}