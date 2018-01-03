package org.dictionary.storage;

import java.util.ArrayList;
import java.util.Collection;
import org.dictionary.model.Term;

public final class Terms {
    private Terms(){
        
    }
    
    public static Collection<Term> all(){
        Collection<Term> terms = new ArrayList<>();
        terms.add(new Term("Class"));
        terms.add(new Term("Method"));
        terms.add(new Term("Interface"));
        terms.add(new Term("Packege"));
        return terms;
    }
}
