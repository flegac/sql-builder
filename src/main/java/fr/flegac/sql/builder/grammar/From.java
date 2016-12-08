package fr.flegac.sql.builder.grammar;

public class From {
  private final Select select;

  private final String fromClause;

  public From(final Select select, final String fromClause) {
    super();
    this.select = select;
    this.fromClause = fromClause;
  }

  public String build() {
    return new StringBuilder("SELECT ").append(select.getSelectClause()).append(" FROM ").append(fromClause).toString();
  }

  public OrderBy orderBy(final String orderByClause) {
    return where("1=1").orderBy(orderByClause);
  }

  public Where where(final String whereClause) {
    return new Where(this, whereClause);
  }
}