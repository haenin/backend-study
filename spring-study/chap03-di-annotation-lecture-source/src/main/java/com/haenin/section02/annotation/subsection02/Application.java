package com.haenin.section02.annotation.subsection02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(
                        "com/haenin/section02");

        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

        PokemonService pokemonService =
                context.getBean("pokemonServiceQualifier",PokemonService.class);
        pokemonService.pokemonAttack();
    }
}
