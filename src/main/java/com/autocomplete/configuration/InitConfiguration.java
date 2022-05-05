package com.autocomplete.configuration;

import com.autocomplete.ds.Trie;
import com.autocomplete.model.Word;
import com.autocomplete.repository.AutoCompleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class InitConfiguration implements ApplicationRunner
{
    @Autowired
    private AutoCompleteRepository autoCompleteRepository;

    @Autowired
    private Trie trie;

    @Value("${initialize_content_file_name}")
    private String fileNameForContent;

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        initTrieFromDB();
    }

    private void initTrieFromDB()
    {
        insertFileContentToDB();
        List<Word> wordList = autoCompleteRepository.findAll();

        for (Word wordItem : wordList)
        {
            trie.add(wordItem.getValue());
        }
    }

    private void insertFileContentToDB()
    {
        Resource resource = new ClassPathResource(fileNameForContent);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                Word wordItem = new Word(line);
                autoCompleteRepository.save(wordItem);
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException("Failed to insert data", e);
        }
    }
}
