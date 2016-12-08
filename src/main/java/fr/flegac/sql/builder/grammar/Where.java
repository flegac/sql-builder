package fr.flegac.sql.builder.grammar;

public class Where {
    private From from;

    private String whereClause;

    public Where(From from, String whereClause) {
        super();
        this.from = from;
        this.whereClause = whereClause;
    }

    public OrderBy orderBy(String orderByClause) {
        return new OrderBy(this, orderByClause);
    }

    public From getFrom() {
        return from;
    }

    public String getWhereClause() {
        return whereClause;
    }

    public String build() {
        return new StringBuilder(from.build()).append(" WHERE ").append(whereClause).toString();
    }
}