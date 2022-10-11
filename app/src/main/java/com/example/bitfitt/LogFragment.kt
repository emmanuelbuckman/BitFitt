package com.example.bitfitt

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class LogFragment : Fragment() {
    private val days = mutableListOf<DAY>()
    private lateinit var bitRv: RecyclerView
//    private lateinit var communicator: Communicator
//
//    private lateinit var subBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_log, container, false)

        bitRv = view.findViewById<RecyclerView>(R.id.sleepRv)

        val bitAdapter = BitFitAdapter(view.context, days)

        bitRv.adapter = bitAdapter
        bitRv.layoutManager = LinearLayoutManager(view.context).also{
            val dividerItemDecoration = DividerItemDecoration(view.context, it.orientation)
            bitRv.addItemDecoration(dividerItemDecoration)
        }


        lifecycleScope.launch{
            (activity?.application as BitFitApplication).db.dayDao().getAll().collect{ databaseList ->
                databaseList.map{ entity ->
                    DAY(
                        entity.date,
                        entity.hours,
                        entity.comments,
                        entity.rating
                    )
                }.also{mappedList ->
                    days.clear()
                    days.addAll(mappedList)
                    bitAdapter.notifyDataSetChanged()
                }

            }
        }

        return view
    }


}