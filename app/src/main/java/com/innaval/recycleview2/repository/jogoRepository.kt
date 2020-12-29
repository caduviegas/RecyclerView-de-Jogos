package com.innaval.recycleview2.repository

import android.content.ContentValues
import android.content.Context
import com.innaval.recycleview2.datasource.DatabaseDefinitions
import com.innaval.recycleview2.datasource.DatabaseHelper
import com.innaval.recycleview2.model.Jogo

class jogoRepository(context: Context) {


    private val dbHelper = DatabaseHelper(context)


    fun save(jogo: Jogo): Int{
        // Colocar o banco em modo escrita
        val db = dbHelper.writableDatabase

        //Criar um mapa com os valores que serão inseridos
        val valores = ContentValues()
        valores.put(DatabaseDefinitions.Jogo.Columns.TITULO, jogo.titulo)
        valores.put(DatabaseDefinitions.Jogo.Columns.PRODUTORA, jogo.produtora)
        valores.put(DatabaseDefinitions.Jogo.Columns.NOTA, jogo.notaJogo)
        valores.put(DatabaseDefinitions.Jogo.Columns.CONSOLE, jogo.console)
        valores.put(DatabaseDefinitions.Jogo.Columns.ZERADO, jogo.zerado)

        // Inserir os dados no banco
        val id = db.insert(DatabaseDefinitions.Jogo.TABLE_NAME, null, valores)
        return id.toInt()
    }
    fun update(jogo:Jogo){

    }

    fun delete(id: Int){

    }

    fun getJogos(){

    }

    fun getJogo(id: Int){

    }
}