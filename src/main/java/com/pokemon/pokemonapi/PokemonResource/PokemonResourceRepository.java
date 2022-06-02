package com.pokemon.pokemonapi.PokemonResource;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonResourceRepository extends CrudRepository<PokemonResource, Long> {
}