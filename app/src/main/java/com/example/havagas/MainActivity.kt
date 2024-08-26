package com.example.havagas

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

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

        //Celular
        cbAdicionarCelular.setOnCheckedChangeListener{_, isChecked -> etCelular.visibility =
            if (isChecked) View.VISIBLE else View.GONE}

        //Formação
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

        //Butão
        findViewById<Button>(R.id.btn_salvar).setOnClickListener{
            val nomeCompleto = etNomeCompleto.text.toString()
            val email = etEmail.text.toString()
            val receberEmail = if (cbReceberEmail.isChecked) "Sim" else "Não"
            val telefone = etTelefone.text.toString()
            val tipoTelefone = when (rgTipoTelefone.checkedRadioButtonId) {
                R.id.rb_comercial -> "Comercial"
                R.id.rb_residencial -> "Residencial"
                else -> "Não tem moradia"
            }
            val celular = if (cbAdicionarCelular.isChecked) etCelular.text.toString() else "Tem não"
            val sexo = spSexo.selectedItem.toString()
            val dataNascimento = etDataNascimento.text.toString()
            val formacao = spFormacao.selectedItem.toString()
            val anoFormatura = etAnoFormatura.text.toString()
            val anoConclusao = etAnoConclusao.text.toString()
            val instituicao = etInstituicao.text.toString()
            val tituloMonografia = etTituloMonografia.text.toString()
            val orientador = etOrientador.text.toString()
            val vagasInteresse = etVagasInteresse.text.toString()

            val msg = StringBuilder()
            msg.append("Nome completo: $nomeCompleto\n")
            msg.append("E-mail: $email (Receber e-mails: $receberEmail)\n")
            msg.append("Telefone: $telefone ($tipoTelefone)\n")
            if (cbAdicionarCelular.isChecked) {
                msg.append("Celular: $celular\n")
            }
            msg.append("Sexo: $sexo\n")
            msg.append("Data de nascimento: $dataNascimento\n")
            msg.append("Formação: $formacao\n")
            if (formacao == "Fundamental" || formacao == "Médio") {
                msg.append("Ano da formatura: $anoFormatura\n")
            } else if (formacao == "Graduação" || formacao == "Especialização") {
                msg.append("Ano de conclusão: $anoConclusao\n")
                msg.append("Instituição: $instituicao\n")
            } else if (formacao == "Mestrado" || formacao == "Doutorado") {
                msg.append("Ano de conclusão: $anoConclusao\n")
                msg.append("Instituição: $instituicao\n")
                msg.append("Titulo da monografia: $tituloMonografia\n")
                msg.append("Orientador: $orientador\n")
            }
            msg.append("Vagas de interesse: $vagasInteresse")

            AlertDialog.Builder(this)
                .setTitle("Dados do cadastro")
                .setMessage(msg.toString())
                .setPositiveButton("Yeah", null)
                .show()
        }

        //Outro Butão
        findViewById<Button>(R.id.btn_limpar).setOnClickListener {
            etNomeCompleto.text.clear()
            etEmail.text.clear()
            cbReceberEmail.isChecked = false
            etTelefone.text.clear()
            rgTipoTelefone.clearCheck()
            cbAdicionarCelular.isChecked = false
            etCelular.text.clear()
            etCelular.visibility = View.GONE
            spSexo.setSelection(0)
            etDataNascimento.text.clear()
            spFormacao.setSelection(0)
            etAnoFormatura.text.clear()
            etAnoConclusao.text.clear()
            etInstituicao.text.clear()
            etTituloMonografia.text.clear()
            etOrientador.text.clear()
            etAnoFormatura.visibility = View.GONE
            etAnoConclusao.visibility = View.GONE
            etInstituicao.visibility = View.GONE
            etTituloMonografia.visibility = View.GONE
            etOrientador.visibility = View.GONE
            etVagasInteresse.text.clear()
        }
    }
}