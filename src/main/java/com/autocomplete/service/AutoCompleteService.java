package com.autocomplete.service;

import com.autocomplete.model.Word;

import java.util.List;
import java.util.Optional;

public interface AutoCompleteService
{
    List<String> getWordsByPrefix(String prefix);
    List<String> getWordsByPrefixInsensitive(String prefix);
    Optional<Word> insertNewWord(String word);
}
