package io.github.manuelernesto.androidroomdemo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import io.github.manuelernesto.androidroomdemo.R
import io.github.manuelernesto.androidroomdemo.data.Evento
import kotlinx.android.synthetic.main.item_event.view.*

class EventoAdapter(
    private val eventos: List<Evento>
) : RecyclerView.Adapter<EventoAdapter.EventoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = EventoHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_event, parent, false)
    )


    override fun onBindViewHolder(holder: EventoHolder, position: Int) {
        holder.view.txt_title_list.text = eventos[position].titulo
        holder.view.txt_description_list.text = eventos[position].descricao
        holder.view.txt_speaker_list.text = eventos[position].palestrante
        holder.view.txt_date_list.text = eventos[position].data

        holder.view.setOnClickListener {
            val action = EventsFragmentDirections.actionEventFragmentToNewFragment()
            action.evento = eventos[position]
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount() = eventos.size

    class EventoHolder(val view: View) : RecyclerView.ViewHolder(view)

}