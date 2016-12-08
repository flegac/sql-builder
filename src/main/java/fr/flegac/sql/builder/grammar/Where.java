package fr.flegac.sql.builder.grammar;

import fr.flegac.sql.builder.SQLWhereBuilder;

public class Where {
  private final From from;

  private String whereClause;

  public Where(final From from, final String whereClause) {
    super();
    this.from = from;
    this.whereClause = whereClause;
  }

  public String build() {
    return new StringBuilder(from.build()).append(" WHERE ").append(whereClause).toString();
  }

  public OrderBy orderBy(final String orderByClause) {
    return new OrderBy(this, orderByClause);
  }

  public Where where(final String whereClause) {
    this.whereClause = SQLWhereBuilder.allTrue(this.whereClause, whereClause);
    return this;
  }
}