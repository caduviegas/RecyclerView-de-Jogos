package com.innaval.recycleview2.datasource

import com.innaval.recycleview2.model.Jogo

class Datasource {
    companion object {
        fun getJogos():ArrayList<Jogo>{
            var jogos = ArrayList<Jogo>()

            jogos.add(Jogo(100,"Residente Evil", "",5.0f, "PS4"))

            return jogos
        }
    }
}