# sql-builder
This library allows to build SQL queries in an object oriented ways.

Its is easy to create a domain specific builder by overriding the Select, From, Where and OrderBy classes.

It is compatible with Nuxeo NXQL request.



#Exemple
The following sql-builder Java code
```Java
import static fr.flegac.sql.SQL.*;
import static fr.flegac.sql.SQLWhereBuilder.*;

String query = select("field1, field2").from("documentType")
    .where(allTrue(
        "A = B",
        anyTrue(like("fdsfdq", pattern1), like("fdsfdq", pattern2)),
        anyTrue("X < 3", "Z = 4"),
        in("Y", 1, 3, 5, "TOTO", 4.5, 3l, 43.21f),
        not(in("K", 1, 2, 3))))
    .orderBy("A").build();
```
and pure java code
```Java
String expected = "SELECT field1, field2 FROM documentType"
    + " WHERE (A = B"
    + " AND ((X LIKE '%SUFFIX') OR (Y LIKE 'PREFIX%'))"
    + " AND (X < 3 OR Z = 4 OR (X >= 76))"
    + " AND (Y IN ('1' , '3' , '5' , 'TOTO' , '4.5' , '3' , '43.21'))"
    + " AND (NOT (K IN ('1' , '2' , '3'))))"
    + " ORDER BY A";
```
generate the same following query:
```SQL
SELECT field1, field2 FROM documentType
WHERE ('A' = B
  AND ((X LIKE '%SUFFIX') OR (Y LIKE 'PREFIX%'))
  AND (X < 3 OR Z = 4 OR (X >= 76))
  AND (Y IN ('1' , '3' , '5' , 'TOTO' , '4.5' , '3' , '43.21'))
  AND (NOT (K IN ('1' , '2' , '3'))))
ORDER BY A
```


#Nuxeo
Nuxeo is a content manager framework.
https://www.nuxeo.com/

