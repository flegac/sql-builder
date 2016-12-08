package fr.flegac.sql.builder;

import static fr.flegac.sql.builder.SQL.select;
import static fr.flegac.sql.builder.SQL.selectAllFields;
import static fr.flegac.sql.builder.SQLWhereBuilder.allTrue;
import static fr.flegac.sql.builder.SQLWhereBuilder.anyTrue;
import static fr.flegac.sql.builder.SQLWhereBuilder.in;
import static fr.flegac.sql.builder.SQLWhereBuilder.like;
import static fr.flegac.sql.builder.SQLWhereBuilder.not;
import static fr.flegac.sql.builder.SQLWhereBuilder.operator;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class SQLShould {

    @Test
    public void buildSQLRequest() {
        // given
        String expected = "SELECT * FROM documentType";

        // when
        String query = selectAllFields().from("documentType").build();

        // then
        assertThat(query).isEqualTo(expected);
    }

    @Test
    public void buildSQLWithOrderByWithoutWhereClause() {
        // given
        String expected = "SELECT * FROM dataBase WHERE 1=1 ORDER BY X, Y ASC, Z DESC";

        // when
        String query = selectAllFields().from("dataBase")
            .orderBy("X, Y ASC, Z DESC").build();

        // then
        System.out.println(query);
        assertThat(query).isEqualTo(expected);
    }

    @Test
    public void buildSQLWithMultipleWhereClause() {
        // given
        String expected = "SELECT * FROM database WHERE clause1 AND clause2 AND (choice1 OR choice2 OR choice3)";

        // when
        String query = selectAllFields().from("database")
            .where("clause1")
            .where("clause2")
            .where(anyTrue("choice1", "choice2", "choice3"))
            .build();

        // then
        System.out.println(query);
        assertThat(query).isEqualTo(expected);
    }

    @Test
    public void buildSQLWithWhereClause() {
        // given
        String pattern1 = "%SUFFIX";
        String pattern2 = "PREFIX%";
        String expected = "SELECT field1, field2 FROM documentType"
            + " WHERE ('A' = B"
            + " AND ((X LIKE '%SUFFIX') OR (Y LIKE 'PREFIX%'))"
            + " AND (X < 3 OR Z = 4 OR (X >= 76))"
            + " AND (Y IN ('1' , '3' , '5' , 'TOTO' , '4.5' , '3' , '43.21'))"
            + " AND (NOT (K IN ('1' , '2' , '3'))))"
            + " ORDER BY A";

        // when
        String query = select("field1, field2").from("documentType")
            .where(allTrue(
                "'A' = B",
                anyTrue(like("X", pattern1), like("Y", pattern2)),
                anyTrue("X < 3", "Z = 4", operator(">=", "X", 76)),
                in("Y", 1, 3, 5, "TOTO", 4.5, 3l, 43.21f),
                not(in("K", 1, 2, 3))))
            .orderBy("A").build();
        // then
        System.out.println(query);
        Assertions.assertThat(query).isEqualTo(expected);
    }

}
