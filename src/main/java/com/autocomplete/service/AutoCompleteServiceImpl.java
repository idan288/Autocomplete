package com.autocomplete.service;

import com.autocomplete.ds.Trie;
import com.autocomplete.exception.AutoCompleteException;
import com.autocomplete.model.Word;
import com.autocomplete.repository.AutoCompleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

@Service
public class AutoCompleteServiceImpl implements AutoCompleteService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AutoCompleteServiceImpl.class);

    @Autowired
    private final AutoCompleteRepository autoCompleteRepository;

    @Autowired
    private final Trie trie;

    public AutoCompleteServiceImpl(AutoCompleteRepository autoCompleteRepository, Trie trie)
    {
        this.autoCompleteRepository = autoCompleteRepository;
        this.trie = trie;
    }

    @Override
    public List<String> getWordsByPrefix(String prefix)
    {
       return trie.getAllWordsByPrefSensitive(prefix);
    }

    @Override
    public List<String> getWordsByPrefixInsensitive(String prefix)
    {
        return trie.getAllWordsByPrefInsensitive(prefix);
    }

    @Override
    public Optional<Word> insertNewWord(String word)
    {
        Optional<Word> wordFromOpt = autoCompleteRepository.findByValue(word);

        if (wordFromOpt.isPresent())
        {
            String errMsg = "[" + word + "]"+ " already exist in DB";
            LOGGER.error(errMsg);
            throw new AutoCompleteException(errMsg, HttpStatus.BAD_REQUEST);
        }

        Word wordItem = new Word(word);
        autoCompleteRepository.save(wordItem);
        trie.add(word);
        return Optional.of(wordItem);
    }
}