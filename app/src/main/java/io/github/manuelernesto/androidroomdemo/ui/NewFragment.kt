package io.github.manuelernesto.androidroomdemo.ui

import android.os.Bundle
import android.text.format.DateFormat
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
    private var mEvento: Evento? = null

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

        arguments?.let {
            setEventWithArgs(it)
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
        launch {
            context?.let {
                val evento = Evento(
                    id = mEvento!!.id,
                    titulo = ed_nome.text.toString(),
                    descricao = et_descricao.text.toString(),
                    palestrante = et_palestrante.text.toString(),
                    // data = ed_data.text.toString()
                )

                EventoDB.getDatabase(it).dao().actualizar(evento)
                Toast.makeText(it, "Actualizado com sucesso", Toast.LENGTH_LONG).show()
                goToMain()
            }
        }
    }

    private fun save() {
        launch {
            context?.let {
                val evento = Evento(
                    titulo = ed_nome.text.toString(),
                    descricao = et_descricao.text.toString(),
                    palestrante = et_palestrante.text.toString(),
                    data = mData!!
                )

                EventoDB.getDatabase(it).dao().salvar(evento)
                Toast.makeText(it, "Evento Salvo!", Toast.LENGTH_LONG).show()
                cleanField()
            }
        }
    }

    private fun delete() {
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Deseja apagar?")
            setNegativeButton("Não") { _, _ ->

            }
            setPositiveButton("Sim") { _, _ ->
                launch {
                    context.let {
                        EventoDB.getDatabase(it).dao().apagar(mEvento!!)
                        Toast.makeText(it, "Apagado com sucesso", Toast.LENGTH_LONG).show()
                        goToMain()
                    }
                }

            }
        }.create().show()
    }

    private fun setEventWithArgs(bundle: Bundle) {
        val calendar = Calendar.getInstance()
        val dateFormat = DateFormat.getDateFormat(requireContext())

        mEvento = NewFragmentArgs.fromBundle(bundle).evento

        mEvento?.let {

            calendar.timeInMillis = it.data

            ed_nome.setText(it.titulo)
            et_descricao.setText(it.descricao)
            et_palestrante.setText(it.palestrante)
            // ed_data.date = dateFormat.format(calendar.time)

            btn_Salvar.visibility = View.INVISIBLE
            btn_update.visibility = View.VISIBLE
            btn_delete.visibility = View.VISIBLE
        }
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