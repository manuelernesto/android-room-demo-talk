package io.github.manuelernesto.androidroomdemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import io.github.manuelernesto.androidroomdemo.R
import io.github.manuelernesto.androidroomdemo.util.StandardFragment
import kotlinx.android.synthetic.main.fragment_events.*


class EventsFragment : StandardFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab_new.setOnClickListener {
            findNavController().navigate(R.id.action_EventFragment_to_newFragment)
        }

    }
}