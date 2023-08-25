package com.example.homework_3m_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class FirstFragment : Fragment() {

    private var counter = 0
    private lateinit var counterTextView: TextView
    private lateinit var plusButton: Button
    private lateinit var fragmentButton: Button
    private  var isItSecondTime: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        counterTextView = view.findViewById(R.id.tv_counter)
        plusButton = view.findViewById(R.id.button_plus)
        fragmentButton = view.findViewById(R.id.button_next_fragment)

        counterTextView.text = counter.toString()

        plusButton.setOnClickListener {
           if(counter < 10 && !isItSecondTime){
               counter++
           }else if(counter == 10 || isItSecondTime && counter != 0 ){
               plusButton.text = "-1"
               counter--
               isItSecondTime = true
           } else if(counter == 0 && isItSecondTime){
               plusButton.text = "+1"
               fragmentButton.visibility = View.VISIBLE
               isItSecondTime = false
           }
            counterTextView.text = counter.toString()
        }
        fragmentButton.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val secondFragment = SecondFragment.newInstance(counter.toString())
            fragmentManager.beginTransaction()
                .replace(R.id.container, secondFragment)
                .addToBackStack(null)
                .commit()
        }
    }


}