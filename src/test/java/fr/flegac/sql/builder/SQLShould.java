package fr.flegac.sql.builder;

import static fr.flegac.sql.builder.SQL.selectAllFields;
import static fr.flegac.sql.builder.SQLWhereBuilder.allTrue;
import static fr.flegac.sql.builder.SQLWhereBuilder.anyTrue;
import static fr.flegac.sql.builder.SQLWhereBuilder.in;
import static fr.flegac.sql.builder.SQLWhereBuilder.not;
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
    public void buildSQLWithWhereClause() {
        // given
        String expected = "SELECT * FROM documentType WHERE "
            + "(A = B"
            + " AND (X < 3 OR Z = 4)"
            + " AND (Y IN ('1' , '3' , '5' , 'TOTO' , '4.5' , '3' , '43.21'))"
            + " AND (NOT (K IN ('1' , '2' , '3'))))"
            + " ORDER BY A";

        // when
        String query = selectAllFields().from("documentType")
            .where(allTrue(
                "A = B",
                anyTrue("X < 3", "Z = 4"),
                in("Y", 1, 3, 5, "TOTO", 4.5, 3l, 43.21f),
                not(in("K", 1, 2, 3))))
            .orderBy("A").build();
        // then
        System.out.println(query);
        Assertions.assertThat(query).isEqualTo(expected);
    }

}
