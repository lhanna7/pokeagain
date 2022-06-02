package com.pokemon.pokemonapi.PokemonResource;

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
public class PokemonResourceController {
    @Autowired
    private PokemonResourceService pokemonResourceService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Iterable<PokemonResource>> list() {
        Iterable<PokemonResource> pokemonResources = pokemonResourceService.list();
        return createHashPlural(pokemonResources);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, PokemonResource> read(@PathVariable Long id) {
        PokemonResource pokemonResource = pokemonResourceService
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No pokepal with that ID"));
        return createHashSingular(pokemonResource);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, PokemonResource> create(@Validated @RequestBody PokemonResource pokemonResource) {
        PokemonResource createdPokemon = pokemonResourceService.create(pokemonResource);
        return createHashSingular(createdPokemon);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, PokemonResource> update(@RequestBody PokemonResource pokemonResource, @PathVariable Long id) {
        PokemonResource updatedPokemon = pokemonResourceService
                .update(pokemonResource)
                .orElseThrow(() -> new ResourceNotFoundException("No pokepal with that ID"));

        return createHashSingular(updatedPokemon);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        pokemonResourceService.deleteById(id);
    }

    private Map<String, PokemonResource> createHashSingular(PokemonResource pokemonResource) {
        Map<String, PokemonResource> response = new HashMap<String, PokemonResource>();
        response.put("pokemonResource", pokemonResource);

        return response;
    }

    private Map<String, Iterable<PokemonResource>> createHashPlural(Iterable<PokemonResource> pokemonResources) {
        Map<String, Iterable<PokemonResource>> response = new HashMap<String, Iterable<PokemonResource>>();
        response.put("pokemonResources", pokemonResources);

        return response;
    }
}