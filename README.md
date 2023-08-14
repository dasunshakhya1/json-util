# JSON Util

Welcome to the **json-util** repository! This project aims to This project aims to handle JSON files, which store test
data easily.

## Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Usage](#usage)

## Project Overview

json-util helps to handle JSON strings and Objects easily.

## Features

- Deserialize a JSON string to an Object.

- Deserialize a JSON array string to an Object List.

- Serialise an Object to a JSON string.

- Retrieve and map a JSON string to an object type by providing a class type and an ID value.

### Usage
#### Add maven dependency
```
<dependency>
  <groupId>com.devlab</groupId>
  <artifactId>json-util</artifactId>
  <version>1.0.0</version>
</dependency>

```

```
public class Foo {

    private String id;
    private String bar;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id
    }

    public String getBar() {
        return this.bar;
    }

    public void setBar(String bar) {
        this.bar = bar
    }

}
String jsonString = {"id":"1","bar":"foobar"};
String jsonArray = [{"id":"1","bar":"foobar"},{"id":"2","bar":"doobar"}]

// Deserialize a JSON string to an Object.
Foo foo = ObjectMapperUtil.mapStringToObject(Foo.class,"1");

//Deserialize a JSON array string to an Object List.
List<Foo> fooList = ObjectMapperUtil.mapStringArrayToObjectArray(Foo.class,jsonArray);

// Serialise an Object to a JSON string.
String fooString = ObjectMapperUtil.mapObjectToString(foo);
```
