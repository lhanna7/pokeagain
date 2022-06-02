package com.pokemon.pokemonapi.PokemonResource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonResourceService {
    @Autowired
    private PokemonResourceRepository pokemonResourceRepository;

    public Iterable<PokemonResource> list() {
        return pokemonResourceRepository.findAll();
    }

    public Optional<PokemonResource> findById(Long id) {
        return pokemonResourceRepository.findById(id);
    }

    public PokemonResource create(PokemonResource pokemonResource) {
        return pokemonResourceRepository.save(pokemonResource);
    }

    public Optional<PokemonResource> update(PokemonResource pokemonResource) {
        Optional<PokemonResource> foundPokemon = pokemonResourceRepository.findById(pokemonResource.getId());

        if (foundPokemon.isPresent()) {
            PokemonResource updatedPokemon = foundPokemon.get();
            updatedPokemon.setName(pokemonResource.getName());
            updatedPokemon.setId(pokemonResource.getId());
            updatedPokemon.setImageUrl(pokemonResource.getImageUrl());

            pokemonResourceRepository.save(updatedPokemon);
            return Optional.of(updatedPokemon);
        } else {
            return Optional.empty();
        }
    }

    public void deleteById(Long id) {
        pokemonResourceRepository.deleteById(id);
    }
}