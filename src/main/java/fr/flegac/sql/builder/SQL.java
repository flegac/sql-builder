package fr.flegac.sql.builder;

import fr.flegac.sql.builder.grammar.Select;

public class SQL {
    public static final String ALL_FIELDS = "*";

    private SQL() {
        super();
    }

    public static Select select(String selectClause) {
        return new Select(selectClause);
    }

    public static Select selectAllFields() {
        return select(ALL_FIELDS);
    }

}
