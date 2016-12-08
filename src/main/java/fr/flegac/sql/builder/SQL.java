package fr.flegac.sql.builder;

import fr.flegac.sql.builder.grammar.Select;

public class SQL {
  public static final String ALL_FIELDS = "*";

  public static Select select(final String selectClause) {
    return new Select(selectClause);
  }

  public static Select selectAllFields() {
    return select(ALL_FIELDS);
  }

  private SQL() {
  }

}
