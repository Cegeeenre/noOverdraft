package com.example.nooverdraft.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.nooverdraft.R
import com.example.nooverdraft.adapter.DepenseAdapter

class ListActivity : Fragment() {


    override fun onCreate(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_list)
        val view = inflater?.inflate(R.layout.activity_list, container, false)

        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recyclerView)
        horizontalRecyclerView.adapter = DepenseAdapter()

        return view

    }
}