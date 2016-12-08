package fr.flegac.sql.builder;

import java.util.Arrays;
import java.util.Collection;

public class SQLWhereBuilder {
    public static final String TRUE = "(1=1)";

    public static final String FALSE = "(1=0)";

    private static final String COMMA = ",";

    private static final String QUOTE = "'";

    private static final String CLOSE = ")";

    private static final String OPEN = "(";

    private static final String SPACE = " ";

    private static final String NOT = "NOT";

    private static final String IN = "IN";

    private static final String LIKE = "LIKE";

    private static final String OR = "OR";

    private static final String AND = "AND";

    private SQLWhereBuilder() {
        super();
    }

    public static String allTrue(String... clauses) {
        return jonction(AND, clauses);
    }

    public static String anyTrue(String... clauses) {
        return jonction(OR, clauses);
    }

    public static String like(String leftValue, String rightValue) {
        return operator(LIKE, leftValue, rightValue);
    }

    public static String in(String value, Object... list) {
        return in(value, Arrays.asList(list));
    }

    public static String in(String value, Collection<?> list) {
        if (list.isEmpty()) {
            return FALSE;
        }
        return listOperator(IN, value, list);
    }

    public static String not(String predicate) {
        return new StringBuilder(OPEN).append(NOT).append(SPACE).append(predicate).append(CLOSE).toString();
    }

    public static String operator(String op, Object leftValue, Object rightValue) {
        return new StringBuilder(OPEN).append(leftValue)
            .append(SPACE).append(op).append(SPACE)
            .append(rightValue).append(CLOSE).toString();
    }

    private static String jonction(String op, String... clauses) {
        return new StringBuilder(OPEN).append(String.join(SPACE + op + SPACE, clauses)).append(CLOSE).toString();
    }

    private static String listOperator(String op, String value, Collection<?> list) {
        return new StringBuilder(OPEN).append(value)
            .append(SPACE).append(op).append(SPACE)
            .append(listFormating(list)).append(CLOSE).toString();
    }

    private static String listFormating(Collection<?> list) {
        StringBuilder result = new StringBuilder(OPEN);

        for (Object o : list) {
            if (o != list.iterator().next()) {
                result.append(SPACE).append(COMMA).append(SPACE);
            }
            result.append(QUOTE).append(o).append(QUOTE);
        }
        result.append(CLOSE);
        return result.toString();
    }

}
