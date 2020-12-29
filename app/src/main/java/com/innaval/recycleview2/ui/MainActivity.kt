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
import com.innaval.recycleview2.datasource.Datasource

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniciarRecyclerView()

        insertToolbar()

        findViewById<FloatingActionButton>(R.id.buttonCadastrarJogo).setOnClickListener(this)

    }

    private fun insertToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar!!.title = "My Game App"
    }

    override fun onClick(v: View?) {
        if (v!!.id == R.id.buttonCadastrarJogo) {
            val intent = Intent(this, CadastroJogoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun iniciarRecyclerView() {

        findViewById<RecyclerView>(R.id.recyclerViewJogos).layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false)
        findViewById<RecyclerView>(R.id.recyclerViewJogos).adapter = JogosAdapter(
                Datasource.getJogos())

    }


}

