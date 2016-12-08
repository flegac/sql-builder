package fr.flegac.sql.builder.grammar;

public class Select {
    private String selectClause;

    public Select(String selectClause) {
        super();
        this.selectClause = selectClause;
    }

    public From from(String fromClause) {
        return new From(this, fromClause);
    }

    public String getSelectClause() {
        return selectClause;
    }

}