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

    fun getJogos(): ArrayList<Jogo>{

        //Colocar o banco de dados em modo Leitura
        val db = dbHelper.readableDatabase

        val cursor = db.query(DatabaseDefinitions.Jogo.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null)


        var jogos = ArrayList<Jogo>()

        if(cursor != null){
            while(cursor.moveToNext()){
                var jogo = Jogo(
                    cursor.getInt(cursor.getColumnIndex(DatabaseDefinitions.Jogo.Columns.ID)),
                    cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Jogo.Columns.TITULO)),
                    cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Jogo.Columns.PRODUTORA)),
                    cursor.getFloat(cursor.getColumnIndex(DatabaseDefinitions.Jogo.Columns.NOTA)),
                    cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Jogo.Columns.CONSOLE)),
                    cursor.getInt(cursor.getColumnIndex(DatabaseDefinitions.Jogo.Columns.ZERADO)) == 1
                )
                jogos.add(jogo)
            }
        }
        return jogos

    }

    fun getJogo(id: Int){

    }
}