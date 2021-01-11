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
    fun update(jogo:Jogo): Int {
        //colocar o banco de dados em modo escrita
        val db = dbHelper.writableDatabase

        // Criar um mapa com os valores que serão atualizados no banco
        val valores = ContentValues().apply{
            put(DatabaseDefinitions.Jogo.Columns.TITULO, jogo.titulo)
            put(DatabaseDefinitions.Jogo.Columns.PRODUTORA, jogo.produtora)
            put(DatabaseDefinitions.Jogo.Columns.NOTA, jogo.notaJogo)
            put(DatabaseDefinitions.Jogo.Columns.CONSOLE, jogo.console)
            put(DatabaseDefinitions.Jogo.Columns.ZERADO, jogo.zerado)
        }

       // Criar o critério para a cláusula WHERE
        val selection = "${DatabaseDefinitions.Jogo.Columns.ID} = ?"

        // Criar um Array com a lista de argumentos que serão utilizados para executar a atualização
        val selectionArgs = arrayOf(jogo.id.toString())

        // Chamar o método update do sqlite
        val count = db.update(
                DatabaseDefinitions.Jogo.TABLE_NAME,
                valores,
                selection,
                selectionArgs
        )

        return count

    }

    fun delete(id: Int): Int {

        //Colocar o banco de dados em modo escrita
        val db = dbHelper.writableDatabase

        //Definir a cláusula WHERE da excluir
        val selection = "${DatabaseDefinitions.Jogo.Columns.ID} = ?"

        // Definir os argumentos que serão usados pela instrução DELETE
        val selectionArgs = arrayOf(id.toString())

        // Executar o comando de exclusão

        val deletedRows = db.delete(
                DatabaseDefinitions.Jogo.TABLE_NAME, selection, selectionArgs)

        return deletedRows

    }

    fun getJogos(): ArrayList<Jogo>{

        //Colocar o banco de dados em modo Leitura
        val db = dbHelper.readableDatabase

        //Definir os campos que serão devolvidos na consulta

        val projection = arrayOf(
                DatabaseDefinitions.Jogo.Columns.ID,
                DatabaseDefinitions.Jogo.Columns.TITULO,
                DatabaseDefinitions.Jogo.Columns.CONSOLE,
                DatabaseDefinitions.Jogo.Columns.NOTA)

        // Definir a ordem de exibição da lista. Ordenar pelo titulo

        val sortOrder = "${DatabaseDefinitions.Jogo.Columns.TITULO} ASC"

        val cursor = db.query(DatabaseDefinitions.Jogo.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            sortOrder)


        var jogos = ArrayList<Jogo>()

        if(cursor != null){
            while(cursor.moveToNext()){
                var jogo = Jogo(
                    id = cursor.getInt(cursor.getColumnIndex(DatabaseDefinitions.Jogo.Columns.ID)),
                    titulo = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Jogo.Columns.TITULO)),
                    console = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Jogo.Columns.CONSOLE)),
                     notaJogo = cursor.getFloat(cursor.getColumnIndex(DatabaseDefinitions.Jogo.Columns.NOTA)),

                )
                jogos.add(jogo)
            }
        }
        return jogos

    }

    fun getJogo(id: Int): Jogo {

        //Colocar o banco de dados em modo Leitura
        val db = dbHelper.readableDatabase

        //Definir os campos que serão devolvidos na consulta

        val projection = arrayOf(
                DatabaseDefinitions.Jogo.Columns.ID,
                DatabaseDefinitions.Jogo.Columns.TITULO,
                DatabaseDefinitions.Jogo.Columns.PRODUTORA,
                DatabaseDefinitions.Jogo.Columns.NOTA,
                DatabaseDefinitions.Jogo.Columns.CONSOLE,
                DatabaseDefinitions.Jogo.Columns.ZERADO
        )

        //Definir o filtro que vamos utilizar
        val selection = "${DatabaseDefinitions.Jogo.Columns.ID} = ?"

        // Definir qual é o valor do argumento
        val selectionArgs = arrayOf(id.toString())



        val cursor = db.query(DatabaseDefinitions.Jogo.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null)


        var jogo = Jogo()

        if (cursor != null) {
            cursor.moveToNext()
            jogo.id = cursor.getInt(cursor.getColumnIndex(DatabaseDefinitions.Jogo.Columns.ID))
            jogo.titulo = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Jogo.Columns.TITULO))
            jogo.produtora =  cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Jogo.Columns.PRODUTORA))
            jogo.notaJogo = cursor.getFloat(cursor.getColumnIndex(DatabaseDefinitions.Jogo.Columns.NOTA))
            jogo.console = cursor.getString(cursor.getColumnIndex(DatabaseDefinitions.Jogo.Columns.CONSOLE))
            jogo.zerado = cursor.getInt(cursor.getColumnIndex(DatabaseDefinitions.Jogo.Columns.ZERADO)) == 1

        }

        println("**********${jogo.titulo}")
        return jogo
    }
}