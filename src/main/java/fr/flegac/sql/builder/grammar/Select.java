package fr.flegac.sql.builder.grammar;

public class Select {
  private final String selectClause;

  public Select(final String selectClause) {
    super();
    this.selectClause = selectClause;
  }

  public From from(final String fromClause) {
    return new From(this, fromClause);
  }

  public String getSelectClause() {
    return selectClause;
  }

}