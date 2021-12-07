package com.umlaut.patterntraining.database;

import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class KnownNamesRepository {
    private final Set<String> knownNames = new HashSet<>();

    public Set<String> getKnownNames() {
        return Set.copyOf(knownNames);
    }

    public void addName(String name) {
        knownNames.add(name);
    }
}
