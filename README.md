### Test case Defaults
```
application-test.yaml
```
```
application:
  a: "some string"
  b:
    - 1
    - 2
    - 3
  c:
    - "one"
    - "two"
    - "three"
  m:
    - one: 1
    - two: 2
```
```
Expecting                    Actual
 a == ""                     a == ""
 b == [1,2,3]                b == [1,2,3]
 c == ["one","two","three"]  c == ["one","two","three"]
 m == {"one":1, "two":2}     m == {"one":1, "two":2}
```

### Test case With Empty Lists
```
application-test.yaml
```
```
application:
  a: "some string"
  b:
    - 1
    - 2
    - 3
  c:
    - "one"
    - "two"
    - "three"
  m:
    - one: 1
    - two: 2
```
We are trying to override
- string property with null,
- list properties with empty json array [],
- map with empty json object {}
```
SPRING_APPLICATION_JSON = {
    "application": {
        "a": null,
        "b": [],
        "c": [],
        "m": {}
    }
}
```
```
Expecting                            Actual
 a == null                           a == "some string"
 b == null | empty array list        b == [1,2,3]
 c == null | empty array list        c == ["one","two","three"]
 m == null | empty map               m == {"one":1, "two":2}
```

### Test case With Empty Objects

```
application-test.yaml
```

```
application:
  a: "some string"
  b:
    - 1
    - 2
    - 3
  c:
    - "one"
    - "two"
    - "three"
  m:
    - one: 1
    - two: 2
```
We are trying to override
- string property with null,
- list properties with empty object {},
- map with empty json object {}
```
SPRING_APPLICATION_JSON = {
    "application": {
        "a": null,
        "b": {},
        "c": {},
        "m": {}
    }
}
```
```
Expecting                            Actual
 a == null                           a == "some string"
 b == null | empty array list        b == [1,2,3]
 c == null | empty array list        c == ["one","two","three"]
 m == null | empty map               m == {"one":1, "two":2}
```

### Test case With Empty Strings

```
application-test.yaml
```
```
application:
  a: "some string"
  b:
    - 1
    - 2
    - 3
  c:
    - "one"
    - "two"
    - "three"
  m:
    - one: 1
    - two: 2
```
We are trying to override
- string property with empty string "",
- list properties with empty string "",
- map properties with empty string ""
```
SPRING_APPLICATION_JSON = {
    "application": {
        "a": "",
        "b": "",
        "c": "",
        "m": ""
    }
}
```

```
Expecting                            Actual
 a == null                           a == ""
 b == null | empty array list        b == []
 c == null | empty array list        c == []
 m == null | empty map               m == {}
```

### Test case With Non null Values
```
application-test.yaml
```
```
application:
  a: "some string"
  b:
    - 1
    - 2
    - 3
  c:
    - "one"
    - "two"
    - "three"
  m:
    - one: 1
    - two: 2
```
We are trying to override
- string property with empty string "",
- list properties with non empty json arrays [9, 10] and ["ten", "nine"],
- map properties with non empty json object {"nine": 9}
```
SPRING_APPLICATION_JSON = {
    "application": {
        "a": "",
        "b": [9,10],
        "c": ["ten", "nine"],
        "m": {"nine": 9}
    }
}
```
```
Expecting                    Actual
 a == ""                     a == ""
 b == [9,10]                 b == [9,10]
 c == ["ten","nine"]         c == ["ten","nine"]
 m == {"nine": 9}            m == {"one":1, "two":2, "nine":9}
```

### Test case With Null values

```
application-test.yaml
```

```
application:
  a: "some string"
  b:
    - 1
    - 2
    - 3
  c:
    - "one"
    - "two"
    - "three"
  m:
    - one: 1
    - two: 2
```

We are trying to override
- string property with null,
- list properties with null,
- map properties with null

```
SPRING_APPLICATION_JSON = {
    "application": {
        "a": null,
        "b": null,
        "c": null,
        "m": null
    }
}
```
```
Expecting                    Actual
 a == ""                     a == "some string"
 b == null | empty           b == [1,2,3]
 c == null | empty           c == ["one","two","three"]
 m == null | empty           m == {"one":1, "two":2}
```
