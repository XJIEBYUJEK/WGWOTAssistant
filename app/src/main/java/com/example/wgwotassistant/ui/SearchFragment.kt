package com.example.wgwotassistant.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.wgwotassistant.R
import com.example.wgwotassistant.data.Repository
import com.example.wgwotassistant.data.WG
import com.example.wgwotassistant.data.WgApi
import com.example.wgwotassistant.data.apiReader
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SearchFragment(override val coroutineContext: CoroutineContext = Dispatchers.Main
) : Fragment(),CoroutineScope {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }
    companion object{
        const val ID = "id"
        const val NICKNAME = "nickname"

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = Repository()


        startSearch.setOnClickListener{
            launch {
                if(nicknameSearch.text.toString().length > 2){
                    val nickname = nicknameSearch.text.toString()
                    val rep = repository.getIdForNickname(nickname).await()
                    rep?.apply {


                        val fragment = PlayersFragment()
                        val bundle = Bundle()


                        val flag = data.isNullOrEmpty()
                        if(status == "ok") {
                            if (!flag) {

                                bundle.putString(ID, data.first().account_id.toString())
                                bundle.putString(NICKNAME, data.first().nickname)

                                fragment.arguments = bundle

                                fragmentManager?.beginTransaction()
                                    ?.replace(
                                        R.id.container,
                                        fragment
                                    )      //переход на новый экран
                                    ?.commit()
                            } else {
                                Toast.makeText(
                                    activity,
                                    "Никнейм не существует",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                        else{
                            Toast.makeText(
                                activity,
                                "Error",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
                else{
                    Toast.makeText( activity, "Никнейм должен содержать более 2 символов", Toast.LENGTH_SHORT  ).show()
                }


            }

        }

    }



}