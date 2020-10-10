package io.github.manuelernesto.androidroomdemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.manuelernesto.androidroomdemo.R
import io.github.manuelernesto.androidroomdemo.data.EventoDB
import io.github.manuelernesto.androidroomdemo.util.StandardFragment
import kotlinx.android.synthetic.main.fragment_events.*
import kotlinx.coroutines.launch


class EventsFragment : StandardFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_events.setHasFixedSize(true)
        rv_events.layoutManager = LinearLayoutManager(context)

        launch {
            context?.let {
                val eventos = EventoDB.getDatabase(it).dao().buscarTodos()
                rv_events.adapter = EventoAdapter(eventos)
            }
        }

        fab_new.setOnClickListener {
            findNavController().navigate(R.id.action_EventFragment_to_newFragment)
        }

    }
}