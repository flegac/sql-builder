package fr.flegac.sql.builder.grammar;

public class OrderBy {
  private final Where where;

  private final String orderByClause;

  public OrderBy(final Where where, final String orderByClause) {
    super();
    this.where = where;
    this.orderByClause = orderByClause;
  }

  public String build() {
    return new StringBuilder(where.build()).append(" ORDER BY ").append(orderByClause).toString();
  }
}