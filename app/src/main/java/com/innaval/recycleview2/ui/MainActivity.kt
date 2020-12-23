package com.innaval.recycleview2.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.innaval.recycleview2.R
import com.innaval.recycleview2.adapter.JogosAdapter
import com.innaval.recycleview2.datasource.DataSource

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniciarRecyclerView()
        findViewById<Button>(R.id.buttonCadastrarJogo).setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v!!.id == R.id.buttonCadastrarJogo) {
            val intent = Intent(this, CadastroJogoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun iniciarRecyclerView() {

        findViewById<RecyclerView>(R.id.recyclerViewJogos).layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        findViewById<RecyclerView>(R.id.recyclerViewJogos).adapter = JogosAdapter(DataSource.getJogos())

    }


}

