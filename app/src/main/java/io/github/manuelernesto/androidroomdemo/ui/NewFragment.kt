package io.github.manuelernesto.androidroomdemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import io.github.manuelernesto.androidroomdemo.R
import io.github.manuelernesto.androidroomdemo.data.Evento
import io.github.manuelernesto.androidroomdemo.data.EventoDB
import io.github.manuelernesto.androidroomdemo.util.StandardFragment
import kotlinx.android.synthetic.main.fragment_new.*
import kotlinx.coroutines.launch
import java.util.*


class NewFragment : StandardFragment() {
    private var mData: Long? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ed_data.date = System.currentTimeMillis()
        ed_data.setOnDateChangeListener { _, year, month, day ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, day)
            mData = calendar.timeInMillis
        }


        btn_Salvar.setOnClickListener {
            save()
        }

        btn_update.setOnClickListener {
            update()
        }
        btn_delete.setOnClickListener {
            delete()
        }

    }


    private fun update() {

    }

    private fun save() {

    }

    private fun delete() {
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Deseja apagar?")
            setNegativeButton("Não") { _, _ ->

            }
            setPositiveButton("Sim") { _, _ ->


            }
        }.create().show()
    }

    private fun cleanField() {
        ed_nome.setText("")
        et_descricao.setText("")
        et_palestrante.setText("")
        ed_data.date = System.currentTimeMillis()
    }

    private fun goToMain() {
        val action = NewFragmentDirections.actionNewFragmentToEventFragment()
        Navigation.findNavController(requireView())
            .navigate(action)
    }
}