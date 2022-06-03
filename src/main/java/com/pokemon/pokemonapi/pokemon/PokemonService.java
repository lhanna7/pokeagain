package com.pokemon.pokemonapi.pokemon;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {
    @Autowired
    private PokemonRepository pokemonRepository;

    public Iterable<Pokemon> list() {
        return pokemonRepository.findAll();
    }

    public Optional<Pokemon> findById(Long id) {
        return pokemonRepository.findById(id);
    }

    public Pokemon create(Pokemon pokemonResource) {
        return pokemonRepository.save(pokemonResource);
    }

    public Optional<Pokemon> update(Pokemon pokemonResource) {
        Optional<Pokemon> foundPokemon = pokemonRepository.findById(pokemonResource.getId());

        if (foundPokemon.isPresent()) {
            Pokemon updatedPokemon = foundPokemon.get();
            updatedPokemon.setName(pokemonResource.getName());
            updatedPokemon.setId(pokemonResource.getId());
            updatedPokemon.setImageUrl(pokemonResource.getImageUrl());

            pokemonRepository.save(updatedPokemon);
            return Optional.of(updatedPokemon);
        } else {
            return Optional.empty();
        }
    }

    public void deleteById(Long id) {
        pokemonRepository.deleteById(id);
    }
}