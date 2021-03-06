package com.innaval.recycleview2.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.innaval.recycleview2.R
import com.innaval.recycleview2.adapter.JogosAdapter
import com.innaval.recycleview2.constants.Constants
import com.innaval.recycleview2.repository.JogoRepository

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        insertToolbar()

        findViewById<FloatingActionButton>(R.id.buttonCadastrarJogo).setOnClickListener(this)

    }

    override fun onResume() {
        iniciarRecyclerView()
        super.onResume()
    }

    private fun insertToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar!!.title = "My Game App"
    }

    override fun onClick(v: View?) {
        if (v!!.id == R.id.buttonCadastrarJogo) {
            val intent = Intent(this, CadastroJogoActivity::class.java)
            intent.putExtra("operacao", Constants.OPERACAO_NOVO_CADASTRO)
            startActivity(intent)
        }
    }

    private fun iniciarRecyclerView() {

        findViewById<RecyclerView>(R.id.recyclerViewJogos).layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false)

        val repo = JogoRepository(this)

        findViewById<RecyclerView>(R.id.recyclerViewJogos).adapter = JogosAdapter(
                repo.getJogos())

    }


}

