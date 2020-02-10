package com.example.wgwotassistant.ui

import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wgwotassistant.R
import com.example.wgwotassistant.data.Repository
import com.example.wgwotassistant.data.WgApi
import com.example.wgwotassistant.data.apiReader
import com.example.wgwotassistant.ui.PlayersFragment.Companion.BATTLES
import com.example.wgwotassistant.ui.PlayersFragment.Companion.WINS
import com.example.wgwotassistant.ui.PlayersFragment.Companion.XP
import com.example.wgwotassistant.ui.SearchFragment.Companion.ID
import kotlinx.android.synthetic.main.player_stat.*
import com.example.wgwotassistant.ui.SearchFragment.Companion.NICKNAME
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import kotlin.coroutines.CoroutineContext

class StatFragment() : Fragment(){
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
        val battles = arguments?.getString(BATTLES).toString().toInt()
        val xp = arguments?.getString(XP)
        val wins = arguments?.getString(WINS).toString().toInt()
        var percent = 0.0
        if (battles > 0)
            percent = (wins*10000/battles).toDouble()/100
        battlesText.text = battles.toString()
        percentText.text = "$percent%"


    }
}