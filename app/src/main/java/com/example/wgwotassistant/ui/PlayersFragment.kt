package com.example.wgwotassistant.ui

import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wgwotassistant.R
import com.example.wgwotassistant.data.WgApi
import com.example.wgwotassistant.data.apiReader
import com.example.wgwotassistant.ui.SearchFragment.Companion.ID
import com.example.wgwotassistant.ui.SearchFragment.Companion.NICKNAME
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.fragment_players.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class PlayersFragment(override val coroutineContext: CoroutineContext = Dispatchers.IO) : Fragment(), CoroutineScope {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_players, container, false)
    }
    companion object{
        const val XP = "xp"


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


        val id = arguments?.getString(ID)
        val nickname = arguments?.getString(NICKNAME)
        playerButton.text = nickname
        idText.text = id


        playerButton.setOnClickListener {
            launch {
                val url = "https://api.worldoftanks.ru/wot/account/info/?application_id=${WgApi.API_KEY}&fields=statistics.all&account_id=$id"
                val gson = JsonParser().parse(apiReader(url)).asJsonObject
                val xp = gson["data"].asJsonObject[id].asJsonObject["statistics"].asJsonObject["all"].asJsonObject["xp"].toString()

                val bundle = Bundle()
                val fragment = StatFragment()
                bundle.putString(NICKNAME, nickname)
                bundle.putString(XP, xp)
                fragment.arguments = bundle

                fragmentManager?.beginTransaction()
                    ?.replace(R.id.container,fragment)      //переход на новый экран
                    ?.commit()
            }

        }

    }

}