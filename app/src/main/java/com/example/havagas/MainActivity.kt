package com.example.havagas

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNomeCompleto = findViewById<EditText>(R.id.et_nome_completo)
        val etEmail = findViewById<EditText>(R.id.et_email)
        val cbReceberEmail = findViewById<CheckBox>(R.id.cb_receber_email)
        val etTelefone = findViewById<EditText>(R.id.et_telefone)
        val rgTipoTelefone = findViewById<RadioGroup>(R.id.rg_tipo_telefone)
        val cbAdicionarCelular = findViewById<CheckBox>(R.id.cb_adicionar_celular)
        val etCelular = findViewById<EditText>(R.id.et_celular)
        val spSexo = findViewById<Spinner>(R.id.sp_sexo)
        val etDataNascimento = findViewById<EditText>(R.id.et_data_nascimento)
        val spFormacao = findViewById<Spinner>(R.id.sp_formacao)
        val etAnoFormatura = findViewById<EditText>(R.id.et_ano_formatura)
        val etAnoConclusao = findViewById<EditText>(R.id.et_ano_conclusao)
        val etInstituicao = findViewById<EditText>(R.id.et_instituicao)
        val etTituloMonografia = findViewById<EditText>(R.id.et_titulo_monografia)
        val etOrientador = findViewById<EditText>(R.id.et_orientador)
        val etVagasInteresse = findViewById<EditText>(R.id.et_vagas_interesse)

        cbAdicionarCelular.setOnCheckedChangeListener{_, isChecked -> etCelular.visibility =
            if (isChecked) View.VISIBLE else View.GONE}

        spFormacao.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedFormacao = parent?.getItemAtPosition(position).toString()

                etAnoFormatura.visibility = View.GONE
                etAnoConclusao.visibility = View.GONE
                etInstituicao.visibility = View.GONE
                etTituloMonografia.visibility = View.GONE
                etOrientador.visibility = View.GONE

                when (selectedFormacao) {
                    "Fundamental", "Médio" -> {
                        etAnoFormatura.visibility = View.VISIBLE
                    }
                    "Graduação", "Especialização" -> {
                        etAnoConclusao.visibility = View.VISIBLE
                        etInstituicao.visibility = View.VISIBLE
                    }
                    "Mestrado", "Doutorado" -> {
                        etAnoConclusao.visibility = View.VISIBLE
                        etInstituicao.visibility = View.VISIBLE
                        etTituloMonografia.visibility = View.VISIBLE
                        etOrientador.visibility = View.VISIBLE
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //BOOOOOMMMMMM, o pc explode. XD
            }
        }
    }
}