package com.innaval.recycleview2.datasource

import com.innaval.recycleview2.model.Jogo

class DataSource {
    companion object {
        fun getJogos():ArrayList<Jogo>{
            var jogos = ArrayList<Jogo>()

            jogos.add(Jogo(100,"Residente Evil", 5, "PS4"))
            jogos.add(Jogo(110,"Zombie Army 4", 5, "PS4"))
            jogos.add(Jogo(120,"The Last of Us Part II", 3, "PS4"))
            jogos.add(Jogo(130,"GFTO", 4, "PC"))
            jogos.add(Jogo(140,"Mario e Sonic", 1, "Switch"))
            jogos.add(Jogo(150,"GRID", 3, "PC"))
            jogos.add(Jogo(160,"Concrete Genie", 0, "Switch"))
            jogos.add(Jogo(170,"Gears of War 5", 2, "XBOX-ONE"))
            jogos.add(Jogo(180,"Blazing Chrome", 2, "XBOX-ONE"))
            jogos.add(Jogo(190,"Gato Roboto", 2, "PC"))
            jogos.add(Jogo(200,"Castlevania Anniversary", 4, "XBOX-ONE"))

            return jogos
        }
    }
}