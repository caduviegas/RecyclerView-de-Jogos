package com.innaval.recycleview2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputLayout
import com.innaval.recycleview2.R
import com.innaval.recycleview2.model.Jogo
import com.innaval.recycleview2.repository.jogoRepository

class CadastroJogoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_jogo)

        insertToolbar()
    }



    private fun insertToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar!!.title = intent.getStringExtra("operacao")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_game, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menu_cancelar -> {
               alert()
            }
            R.id.menu_salvar -> {
                if(validarFormulario()){
                    salvarJogo()
                }
            } else -> {
                onBackPressed()
            }
        }

        return true
    }

    private fun salvarJogo() {
        // Criar um Objeto Jogo
        val jogo = Jogo (
                titulo = findViewById<EditText>(R.id.editTextNomeDoJogo).text.toString(),
                produtora = findViewById<EditText>(R.id.editTextProdutoraDoJogo).text.toString(),
                notaJogo = findViewById<RatingBar>(R.id.ratingBarNotaDoJogo).rating,
                console = findViewById<Spinner>(R.id.spinnerConsole).selectedItem.toString(),
                zerado = findViewById<CheckBox>(R.id.checkBoxZerado).isChecked
        )

        // Criar uma instância do repositorio
        val repo = jogoRepository(this)
        val id = repo.save(jogo)


        // Exibir confirmação de inclusão do Registro

        if(id > 0){
            val builderDialog = AlertDialog.Builder(this)
            builderDialog.setTitle("Sucesso!")
            builderDialog.setMessage("Seu Jogo Foi Gravado com Sucesso!\n\nDeseja Cadastrar Outro Jogo?")
            builderDialog.setIcon(R.drawable.ic_baseline_done_green24)

            builderDialog.setPositiveButton("Sim"){_,_ ->
                limparFormulario()
            }

            builderDialog.setNegativeButton("Não"){_,_ ->
                onBackPressed()
            }
            builderDialog.show()
        }
    }

    private fun limparFormulario() {
        findViewById<EditText>(R.id.editTextNomeDoJogo).setText("")
        findViewById<EditText>(R.id.editTextProdutoraDoJogo).setText("")
        findViewById<CheckBox>(R.id.checkBoxZerado).isChecked = false
        findViewById<EditText>(R.id.editTextNomeDoJogo).requestFocus()

    }

    private fun validarFormulario(): Boolean {

        var valida = true
        if(findViewById<EditText>(R.id.editTextNomeDoJogo).length() < 3){
            findViewById<TextInputLayout>(R.id.tilNomeDoJogo).isErrorEnabled = true
            findViewById<TextInputLayout>(R.id.tilNomeDoJogo).error = "Titulo do jogo é Obrigatório!"
            valida = false
        } else {
            findViewById<TextInputLayout>(R.id.tilNomeDoJogo).isErrorEnabled = false
            findViewById<TextInputLayout>(R.id.tilNomeDoJogo).error = null
        }

        if(findViewById<EditText>(R.id.editTextProdutoraDoJogo).length() < 3){
            findViewById<TextInputLayout>(R.id.tilProdutoraDoJogo).isErrorEnabled = true
            findViewById<TextInputLayout>(R.id.tilProdutoraDoJogo).error = "Produtora  do jogo é Obrigatório!"
            valida = false
        } else {
            findViewById<TextInputLayout>(R.id.tilProdutoraDoJogo).isErrorEnabled = false
            findViewById<TextInputLayout>(R.id.tilProdutoraDoJogo).error = null
        }

        return valida
    }

    private fun alert() {
        var builderDialog = AlertDialog.Builder(this)
        builderDialog.setTitle("Cancelar Cadasstro")
        builderDialog.setMessage("Tem Certeza que Deseja Cancelar o Cadastro do seu Jogo?")
        builderDialog.setIcon(R.drawable.ic_baseline_help_red_24)

        builderDialog.setPositiveButton("Sim"){_, _ ->
            onBackPressed()

        }
        builderDialog.setNegativeButton("Não") { _, _ ->
            findViewById<EditText>(R.id.editTextNomeDoJogo).requestFocus()
        }
        builderDialog.show()
    }
}