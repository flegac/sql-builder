package fr.flegac.sql.builder.grammar;

public class OrderBy {
    private Where where;

    private String orderByClause;

    public OrderBy(Where where, String orderByClause) {
        super();
        this.where = where;
        this.orderByClause = orderByClause;
    }

    public String build() {
        return new StringBuilder(where.build()).append(" ORDER BY ").append(orderByClause).toString();
    }
}