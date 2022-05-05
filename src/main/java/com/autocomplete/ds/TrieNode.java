package com.autocomplete.ds;

import java.util.HashMap;
import java.util.Map;

public class TrieNode
{
    private final Map<Character, TrieNode> children;
    private boolean isWordEnd;

    public TrieNode()
    {
        children = new HashMap<>();
        isWordEnd = false;
    }

    public Map<Character, TrieNode> getChildren()
    {
        return children;
    }

    public boolean isWordEnd()
    {
        return isWordEnd;
    }

    public void setIsWordEnd(boolean isWordEnd)
    {
        this.isWordEnd = isWordEnd;
    }
}