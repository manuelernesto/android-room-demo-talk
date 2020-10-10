package io.github.manuelernesto.androidroomdemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import io.github.manuelernesto.androidroomdemo.R
import io.github.manuelernesto.androidroomdemo.data.Evento
import io.github.manuelernesto.androidroomdemo.data.EventoDB
import io.github.manuelernesto.androidroomdemo.util.StandardFragment
import kotlinx.android.synthetic.main.fragment_new.*
import kotlinx.coroutines.launch


class NewFragment : StandardFragment() {

    private var mEvento: Evento? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        arguments?.let {
            mEvento = NewFragmentArgs.fromBundle(it).evento

            mEvento?.let { evento ->
                ed_nome.setText(evento.titulo)
                et_descricao.setText(evento.descricao)
                et_palestrante.setText(evento.palestrante)
                ed_data.setText(evento.data)

                btn_Salvar.visibility = View.INVISIBLE
                btn_update.visibility = View.VISIBLE
                btn_delete.visibility = View.VISIBLE
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


    }

    private fun update() {
        launch {
            context?.let { ctx ->
                val evento = Evento(
                    id = mEvento!!.id,
                    titulo = ed_nome.text.toString(),
                    descricao = et_descricao.text.toString(),
                    palestrante = et_palestrante.text.toString(),
                    data = ed_data.text.toString()
                )

                EventoDB.getDatabase(ctx).dao().actualizar(evento)
                Toast.makeText(ctx, "Actualizado com sucesso", Toast.LENGTH_LONG).show()

            }
        }
    }

    private fun save() {
        launch {
            context?.let { ctx ->
                val evento = Evento(
                    titulo = ed_nome.text.toString(),
                    descricao = et_descricao.text.toString(),
                    palestrante = et_palestrante.text.toString(),
                    data = ed_data.text.toString()
                )

                EventoDB.getDatabase(ctx).dao().salvar(evento)
                Toast.makeText(ctx, "Salvo com sucesso", Toast.LENGTH_LONG).show()
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
                    }
                }
            }
        }.create().show()
    }
}