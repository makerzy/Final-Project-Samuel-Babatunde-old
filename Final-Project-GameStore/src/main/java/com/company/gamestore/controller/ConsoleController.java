package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

@RestController
public class ConsoleController {
    @Autowired
    ConsoleRepository ConRepo;

    // read all console
    @GetMapping("/consoles")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getAllConsole() {
        return ConRepo.findAll();
    }

    // read console by ID
    @GetMapping("/consoles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Console getConsoleById(@PathVariable int id) {
        Optional<Console> console = ConRepo.findById(id);
        return console.orElse(null);
    }

    // update console
    @PutMapping("/consoles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@RequestBody @Valid Console console, @PathVariable Integer id) {
        Optional<Console> console1 = ConRepo.findById(id);
        if (console1.isPresent())
            ConRepo.save(console);

    }

    //  create console
    @PostMapping(value = "/consoles")
    @ResponseStatus(HttpStatus.CREATED)
    public Console addConsole(@RequestBody Console console) {
        return ConRepo.save(console);
    }

    // delete console
    @DeleteMapping("/consoles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable Integer id) {
        ConRepo.deleteById(id);
    }

    // search console by manufacturer
    @GetMapping("/consoles/manufacturers/{manufacturer}")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getConsoleByManufacturer(@PathVariable String manufacturer) {
        return ConRepo.findByManufacturer(manufacturer);
    }
}