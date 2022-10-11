package com.example.bitfitt

import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {

    private lateinit var averageTv: TextView
    private lateinit var minimumTv: TextView
    private lateinit var maximumTv: TextView
    private lateinit var clearButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        averageTv = view.findViewById(R.id.averageTv)
        minimumTv = view.findViewById(R.id.minimumTv)
        maximumTv = view.findViewById(R.id.maximumTv)
        clearButton = view.findViewById(R.id.clearButton)

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
                    update(mappedList)
                }
            }
        }

        clearButton.setOnClickListener{
            lifecycleScope.launch{
                (activity?.application as BitFitApplication).db.dayDao().deleteAll()}
        }


        return view
    }

    private fun update(days: List<DAY>){

        var min: Int = 0
        var max: Int = Int.MAX_VALUE
        var sum: Int = 0

        if (days.isEmpty()){
            averageTv.text = "No Data"
            minimumTv.text = "No Data"
            maximumTv.text = "No Data"

            return
        }

        for (day in days) {

            sum += day.hours!!
            if (day.hours > max){max = day.hours}
            if (day.hours < min){min = day.hours}


//            day.hours?.let{
//                sum += it
//                if (it > max) max = it
//                if (it < min) min = it
//            }
        }

        averageTv.text = (sum / days.size).toString()
        maximumTv.text = max.toString()
        minimumTv.text = min.toString()

    }

}