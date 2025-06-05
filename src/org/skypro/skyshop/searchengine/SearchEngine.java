package org.skypro.skyshop.searchengine;

import org.skypro.skyshop.exceptions.BestResultNotFoundException;
import org.skypro.skyshop.searchable.Searchable;
import org.skypro.skyshop.searchable.SearchableComparator;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class SearchEngine {
    private final Set<Searchable> searchableArray;

    public SearchEngine() {
        this.searchableArray = new HashSet<>();
    }

    public Set<Searchable> search(String substring) throws BestResultNotFoundException {
        Set<Searchable> searchArray = searchableArray.stream()
                .filter(Objects::nonNull)
                .filter(s -> s.searchTerm().contains(substring))
                .collect(Collectors.toCollection(() -> new TreeSet<>(new SearchableComparator())));

        if (searchArray.isEmpty()) {
            throw new BestResultNotFoundException(
                    "Для поискового запроса: <" + substring + ">, не нашлось подходящего результата");
        }

        return searchArray;
    }

    public void add(Searchable object) {
        searchableArray.add(object);
    }
}