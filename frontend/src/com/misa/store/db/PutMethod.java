package com.misa.store.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.misa.store.common.Book;

import java.io.IOException;

public class PutMethod {

    public PutMethod() {
    }

    public Book read(int book_id) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Book bookReceived;
        DataBaseProperties dbProp = new DataBaseProperties();
        bookReceived = objectMapper.readValue(Connect.get(dbProp.get("url") + ":" + dbProp.get("port") + dbProp.get("path") + "/" + book_id), Book.class);

        return bookReceived;
    }
}
