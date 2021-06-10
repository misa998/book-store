package com.misa.store.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.misa.store.common.Book;
import javafx.scene.chart.PieChart;

import javax.swing.*;

public class PostMethod {

    public Book postPaylaod(String payload){

                try {
                    String response = response(payload);
                    ObjectMapper objectMapper = new ObjectMapper();
                    Book book = objectMapper.readValue(response, Book.class);
                    return book;

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getClass(),
                            "Error", JOptionPane.WARNING_MESSAGE);
                    System.out.println(e.getStackTrace());
                }
            return null;

    }

    private String response(String payload) throws Exception {
        DataBaseProperties dbProp = new DataBaseProperties();
        return Connect.post(dbProp.get("url") + ":" + dbProp.get("port") + dbProp.get("path"), payload);
    }
}
