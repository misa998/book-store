package com.misa.store.db;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.misa.store.common.Book;

import java.io.IOException;
import java.net.ConnectException;
import java.util.List;

public class GetMethod {

    public List<Book> getAll() throws IOException, ConnectException {
        ObjectMapper om = new ObjectMapper();
        return om.readValue(connectionGet(), new TypeReference<List<Book>>(){});
    }

    private String connectionGet() throws ConnectException, IOException{
        DataBaseProperties dbProp = new DataBaseProperties();
        String receivedPacket = "";
        receivedPacket = new Connect().get(dbProp.get("url") + ":" + dbProp.get("port") + dbProp.get("path"));

        return receivedPacket;
    }
}
