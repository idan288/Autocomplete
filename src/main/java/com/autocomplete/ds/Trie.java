package com.autocomplete.ds;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class Trie
{
    private final TrieNode root;

    public Trie()
    {

        root = new TrieNode();
    }

    /**
     * Add the given word to the current trie.
     * @param word the word to be added.
     */
    public void add(String word)
    {
        if (word == null || word.length() == 0)
        {
            return;
        }

        TrieNode cur = root;

        for (int i = 0; i < word.length(); i++)
        {
            char ch = word.charAt(i);
            TrieNode node = cur.getChildren().get(ch);

            if (node == null)
            {
                node  = new TrieNode();
                cur.getChildren().put(ch, node);
            }

            cur = node;
        }

        cur.setIsWordEnd(true);
    }

    /***
     * Returning all the words that match the given prefix.
     * <p>
     *     This method will search matching words for case insensitive prefix.
     * </p>
     * @param prefix is the prefix we will use to search the matching words.
     * @return List of words that match to the given prefix in the trie.
     */
    public List<String> getAllWordsByPrefInsensitive(String prefix)
    {
        List<String> words = new LinkedList<>();
        if (prefix == null || prefix.length() == 0)
        {
            return words;
        }

        List<String> allCombinations = getAllCombinations(prefix);

        for (String curComb : allCombinations)
        {
            getAllWordsSensitive(words, curComb);
        }

        return words;
    }

    /***
     * Returning all the words that match the given prefix.
     * <p>
     *     This method will search matching words for case sensitive prefix.
     * </p>
     * @param prefix is the prefix we will use to search the matching words.
     * @return List of words that match to the given prefix in the trie.
     */
    public List<String> getAllWordsByPrefSensitive(String prefix)
    {
        List<String> words = new LinkedList<>();

        if (prefix == null || prefix.length() == 0)
        {
            return words;
        }

        getAllWordsSensitive(words, prefix);

        return words;
    }

    private void getAllWordsSensitive(List<String> words, String pref)
    {
        TrieNode cur = root;

        for (int i = 0; i < pref.length(); i++)
        {
            cur = cur.getChildren().get(pref.charAt(i));

            if (cur == null)
            {
                break;
            }
        }

        searchWords(words, pref, cur);
    }

    private void searchWords(List<String> words, String word, TrieNode cur)
    {
        if (cur == null)
        {
            return;
        }

        if (cur.isWordEnd())
        {
            words.add(word);
        }

        for (Character ch : cur.getChildren().keySet())
        {
            searchWords(words, word+ch, cur.getChildren().get(ch));
        }
    }

    private List<String> getAllCombinations(String input)
    {
        List<String> words = new LinkedList<>();

        if (input == null || input.length() == 0)
        {
            return words;
        }

        int numOfComb = 1 << input.length();
        input = input.toLowerCase();

        for (int i = 0; i < numOfComb; i++)
        {
            char[] currentCombo = input.toCharArray();

            for (int j = 0; j < input.length(); j ++)
            {
                if (((i >> j) & 1) == 1)
                {
                    currentCombo[j] = Character.toUpperCase(currentCombo[j]);
                }
            }

            words.add(new String(currentCombo));
        }

        return words;
    }
}