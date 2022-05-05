package com.autocomplete.controller;

import com.autocomplete.model.Word;
import com.autocomplete.service.AutoCompleteService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("autocomplete")
public class AutoCompleteController
{
    @Autowired
    AutoCompleteService autoCompleteService;

    @GetMapping("sensitive/{pref}")
    public ResponseEntity<List<String>> getWordsByPrefix(@PathVariable @NotNull final String pref)
    {
        return new ResponseEntity<>(autoCompleteService.getWordsByPrefix(pref), HttpStatus.OK);
    }

    @GetMapping("insensitive/{pref}")
    public ResponseEntity<List<String>> getWordsByPrefixInsensitive(@PathVariable @NotNull final String pref)
    {
        return new ResponseEntity<>(autoCompleteService.getWordsByPrefixInsensitive(pref), HttpStatus.OK);
    }

    @PostMapping("add/{word}")
    public ResponseEntity<Word> insertNewWord(@PathVariable @NotNull final String word)
    {
        ResponseEntity<Word> res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Optional<Word> optWord = autoCompleteService.insertNewWord(word);

        if (optWord.isPresent())
        {
            res = new ResponseEntity<>(optWord.get(), HttpStatus.CREATED);
        }

        return res;
    }
}