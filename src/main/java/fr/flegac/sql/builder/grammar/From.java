package fr.flegac.sql.builder.grammar;

public class From {
    private Select select;

    private String fromClause;

    public From(Select select, String fromClause) {
        super();
        this.select = select;
        this.fromClause = fromClause;
    }

    public Where where(String whereClause) {
        return new Where(this, whereClause);
    }

    public OrderBy orderBy(String orderByClause) {
        return where("1=1").orderBy(orderByClause);
    }

    public String build() {
        return new StringBuilder("SELECT ").append(select.getSelectClause()).append(" FROM ").append(fromClause).toString();
    }
}