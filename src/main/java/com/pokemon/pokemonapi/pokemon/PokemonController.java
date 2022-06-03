package com.pokemon.pokemonapi.pokemon;

import java.util.Map;
import java.util.HashMap;

import com.pokemon.pokemonapi.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
@RequestMapping("api/pokemon")
public class PokemonController {
    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Iterable<Pokemon>> list() {
        Iterable<Pokemon> pokemonResources = pokemonService.list();
        return createHashPlural(pokemonResources);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Pokemon> read(@PathVariable Long id) {
        Pokemon pokemonResource = pokemonService
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No pokepal with that ID"));
        return createHashSingular(pokemonResource);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Pokemon> create(@Validated @RequestBody Pokemon pokemonResource) {
        Pokemon createdPokemon = pokemonService.create(pokemonResource);
        return createHashSingular(createdPokemon);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Pokemon> update(@RequestBody Pokemon pokemonResource, @PathVariable Long id) {
        Pokemon updatedPokemon = pokemonService
                .update(pokemonResource)
                .orElseThrow(() -> new ResourceNotFoundException("No pokepal with that ID"));

        return createHashSingular(updatedPokemon);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        pokemonService.deleteById(id);
    }

    private Map<String, Pokemon> createHashSingular(Pokemon pokemonResource) {
        Map<String, Pokemon> response = new HashMap<String, Pokemon>();
        response.put("pokemon", pokemonResource);

        return response;
    }

    private Map<String, Iterable<Pokemon>> createHashPlural(Iterable<Pokemon> pokemonResources) {
        Map<String, Iterable<Pokemon>> response = new HashMap<String, Iterable<Pokemon>>();
        response.put("pokemon", pokemonResources);

        return response;
    }
}