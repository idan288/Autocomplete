package com.autocomplete.repository;

import com.autocomplete.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutoCompleteRepository extends JpaRepository<Word, Integer> {
    Optional<Word> findByValue(String word);
}
