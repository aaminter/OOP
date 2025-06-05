package org.skypro.skyshop.searchable;

import java.util.Comparator;

public class SearchableComparator implements Comparator<Searchable> {
    @Override
    public int compare(Searchable o1, Searchable o2) {
        return o2.searchTerm().length() - o1.searchTerm().length() == 0 ?
                o1.searchTerm().compareTo(o2.searchTerm()) :
                o2.searchTerm().length() - o1.searchTerm().length();
    }
}