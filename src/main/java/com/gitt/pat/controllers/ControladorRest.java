package com.gitt.pat.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:63342"})
public class ControladorRest {
    private final Map<Integer, ModeloContador> contadores = new HashMap<>();

    public ControladorRest() {
        contadores.put(1, new ModeloContador(1, "Under the Dome", "https://static.tvmaze.com/uploads/images/medium_portrait/81/202627.jpg", 6.5, "<p><b>Under the Dome</b> is the story of a small town that is suddenly and inexplicably sealed off from the rest of the world by an enormous transparent dome. The town's inhabitants must deal with surviving the post-apocalyptic conditions while searching for answers about the dome, where it came from and if and when it will go away.</p>", List.of("Drama", "Science-Fiction", "Thriller")));
        contadores.put(2, new ModeloContador(2, "Person of Interest", "https://static.tvmaze.com/uploads/images/medium_portrait/163/407679.jpg", 8.8, "<p>You are being watched. The government has a secret system, a machine that spies on you every hour of every day. I know because I built it. I designed the Machine to detect acts of terror but it sees everything. Violent crimes involving ordinary people. People like you. Crimes the government considered \\\"irrelevant\\\". They wouldn't act so I decided I would. But I needed a partner. Someone with the skills to intervene. Hunted by the authorities, we work in secret. You'll never find us. But victim or perpetrator, if your number is up, we'll find you.</p>", List.of("Action", "Crime", "Science-Fiction")));
        contadores.put(3, new ModeloContador(3, "Bitten", "https://static.tvmaze.com/uploads/images/medium_portrait/0/15.jpg", 7.5, "<p>Based on the critically acclaimed series of novels from Kelley Armstrong. Set in Toronto and upper New York State, <b>Bitten</b> follows the adventures of 28-year-old Elena Michaels, the world's only female werewolf. An orphan, Elena thought she finally found her \\\"happily ever after\\\" with her new love Clayton, until her life changed forever. With one small bite, the normal life she craved was taken away and she was left to survive life with the Pack.</p>", List.of("Drama", "Horror", "Romance")));
    }

    @PostMapping("/HigherOrLower")
    @ResponseStatus(HttpStatus.CREATED)
    public ModeloContador crea(@RequestBody ModeloContador contadorNuevo) {
        contadores.put(contadorNuevo.id(), contadorNuevo);
        return contadorNuevo;
    }

    @GetMapping("/HigherOrLower/{id}")
    public ModeloContador contador(@PathVariable Integer id) {
        return contadores.get(id);
    }

    @PutMapping("/HigherOrLower/Create/{id}/{name}/{image}/{rating}/{summary}/{genres}")
    public ModeloContador actualizaPorNombre(@PathVariable Integer id,@PathVariable String name,@PathVariable String image,@PathVariable Double rating,@PathVariable String summary,@PathVariable List<String > genres) {
        ModeloContador contadorNew = new ModeloContador(id, name, image, rating, summary, genres);
        contadores.put(contadorNew.id(), contadorNew);
        return contadorNew;
    }

    @DeleteMapping("/HigherOrLower/Delete/{name}")
    public ModeloContador elimina(@PathVariable String name) {
        for (ModeloContador contador : contadores.values()) {
            if (contador.name().equalsIgnoreCase(name)) {
                ModeloContador eliminado = contadores.remove(contador.id());
                return eliminado;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Show not found");
    }

    @PutMapping("/HigherOrLower/UpdateName/{name}/{newName}")
    public ModeloContador actualizaNombre(@PathVariable String name, @PathVariable String newName) {
        for (ModeloContador contador : contadores.values()) {
            if (contador.name().equalsIgnoreCase(name)) {
                ModeloContador updatedContador = new ModeloContador(contador.id(), newName, contador.image(), contador.rating(), contador.summary(), contador.genres());
                contadores.put(contador.id(), updatedContador);
                return updatedContador;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Show not found");
    }

    @PutMapping("/HigherOrLower/UpdateImage/{name}/{newImage}")
    public ModeloContador actualizaImagen(@PathVariable String name, @PathVariable String newImage) {
        for (ModeloContador contador : contadores.values()) {
            if (contador.name().equalsIgnoreCase(name)) {
                ModeloContador updatedContador = new ModeloContador(contador.id(), contador.name(), newImage, contador.rating(), contador.summary(), contador.genres());
                contadores.put(contador.id(), updatedContador);
                return updatedContador;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Show not found");
    }

    @PutMapping("/HigherOrLower/UpdateRating/{name}/{newRating}")
    public ModeloContador actualizaRating(@PathVariable String name, @PathVariable Double newRating) {
        for (ModeloContador contador : contadores.values()) {
            if (contador.name().equalsIgnoreCase(name)) {
                ModeloContador updatedContador = new ModeloContador(contador.id(), contador.name(), contador.image(), newRating, contador.summary(), contador.genres());
                contadores.put(contador.id(), updatedContador);
                return updatedContador;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Show not found");
    }

    @PutMapping("/HigherOrLower/UpdateSummary/{name}/{newSummary}")
    public ModeloContador actualizaResumen(@PathVariable String name, @PathVariable String newSummary) {
        for (ModeloContador contador : contadores.values()) {
            if (contador.name().equalsIgnoreCase(name)) {
                ModeloContador updatedContador = new ModeloContador(contador.id(), contador.name(), contador.image(), contador.rating(), newSummary, contador.genres());
                contadores.put(contador.id(), updatedContador);
                return updatedContador;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Show not found");
    }

    @PutMapping("/HigherOrLower/UpdateGenres/{name}/{newGenres}")
    public ModeloContador actualizaGeneros(@PathVariable String name, @PathVariable List<String> newGenres) {
        for (ModeloContador contador : contadores.values()) {
            if (contador.name().equalsIgnoreCase(name)) {
                ModeloContador updatedContador = new ModeloContador(contador.id(), contador.name(), contador.image(), contador.rating(), contador.summary(), newGenres);
                contadores.put(contador.id(), updatedContador);
                return updatedContador;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Show not found");
    }

    @RequestMapping("/HigherOrLower/undefined")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUndefined() {
        return "Resource not found";
    }
}