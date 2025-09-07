package com.haenin.section02.annotation.subsection04.resource;

import com.haenin.section02.annotation.common.Pokemon;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pokemonServiceResource")
public class PokemonService {
    /* 설명. @Resource를 활용한 DI */
    /* 설명. 1. bean을 1개만 주입받고 싶을 때 (필드 주입) */
//    @Resource(name = "pikachu")
//    private Pokemon pokemon;
//
//    public void pokemonAttack() {
//        pokemon.attack();
//    }

    /* 설명. 2. bean 여러개 주입받고 싶을 때 */
    @Resource
    private List<Pokemon> pokemonList;

    public void pokemonAttack() {
        pokemonList.forEach(pokemon -> pokemon.attack());
    }
}
