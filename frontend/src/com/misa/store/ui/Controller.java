package com.misa.store.ui;

import com.misa.store.db.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.misa.store.common.Book;

import javax.swing.*;
import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.List;

public class Controller {
    @FXML
    private TableView<Book> tableView;
    @FXML
    private TableColumn<Book, Integer> tableId;
    @FXML
    private TableColumn<Book, String> tableName;
    @FXML
    private TableColumn<Book, String> tableAuthor;
    @FXML
    private TableColumn<Book, Integer> tableYear;
    @FXML
    private TableColumn<Book, String> tableLanguage;
    @FXML
    private TableColumn<Book, Double> tableScore;
    @FXML
    private TableColumn<Book, String> tableGenre;
    @FXML
    private Button getBtn;
    @FXML
    private TextField txtFid;
    @FXML
    private TextField txtFname;
    @FXML
    private TextField txtFyear;
    @FXML
    private TextField txtFscore;
    @FXML
    private TextField txtFauthor;
    @FXML
    private TextField txtFgenre;
    @FXML
    private TextField txtFlang;
    @FXML
    private Label statusLabel;

    public void initialize(){
        setupTableView();
    }

    @FXML
    public void retrieve(){
        GetMethod getMethod = new GetMethod();
        List<Book> books = Collections.emptyList();
        try {
            books = getMethod.getAll();
        } catch (ConnectException ce){
            JOptionPane.showMessageDialog(null, "Server is down",
                    "Error", JOptionPane.WARNING_MESSAGE);
            serverIsUp(false);
            return;
        } catch (IOException ioe){
            JOptionPane.showMessageDialog(null, "Error while reading",
                    "Error", JOptionPane.WARNING_MESSAGE);
            ioe.printStackTrace();
        }

        serverIsUp(true);
        updateTable(books);
    }

    private void serverIsUp(boolean isUp){
        statusLabel.setText(isUp ? "Online" : "Offline");
    }

    private void updateTable(List<Book> books) {
        tableView.getItems().clear();

        for(Book book : books){
            tableView.getItems().add(book);
        }
    }

    @FXML
    public void send(){
        String payload = getPayload();
        PostMethod post = new PostMethod();
        post.postPaylaod(payload);
    }

    @FXML
    public void delete() {
        Book book = tableView.getSelectionModel().getSelectedItem();
        DeleteMethod deleteMethod = new DeleteMethod();
        if(book == null)
            showMessageForSelection();

        deleteMethod.deleteItem(book.getId());
    }

    private void showMessageForSelection(){
        JOptionPane.showMessageDialog(null, "Select an item",
                "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    public void put(){
        Book book = tableView.getSelectionModel().getSelectedItem();
        PutMethod putMethod = new PutMethod();
        Book bookReceived = null;
        try {
            bookReceived = putMethod.read(book.getId());
        } catch (IOException e){
            e.printStackTrace();
            return;
        } catch (NullPointerException npe){
            showMessageForSelection();
        }

        fillTheFields(bookReceived);
    }

    private void fillTheFields(Book book){
        txtFid.setText(String.valueOf(book.getId()));
        txtFname.setText(book.getName());
        txtFyear.setText(String.valueOf(book.getReleaseYear()));
        txtFscore.setText(String.valueOf(book.getUserScore()));
        txtFauthor.setText(book.getAuthor());
        txtFlang.setText(book.getLanguage());
        txtFgenre.setText(book.getGenre());
    }

    private String getPayload(){
        String payload;
        payload = "{" + "\"id\":\"" + txtFid.getText() + "\"," + "\"name\":\"" + txtFname.getText() + "\","
                    + "\"releaseYear\":\"" + txtFyear.getText() + "\"," + "\"userScore\":\"" + txtFscore.getText() + "\","
                    + "\"author\":\"" + txtFauthor.getText() + "\"," + "\"genre\":\"" + txtFgenre.getText() + "\","
                    + "\"language\":\"" + txtFlang.getText() + "\"" + "}";
        return payload;
    }

    private void setupTableView() {
        tableId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableYear.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
        tableScore.setCellValueFactory(new PropertyValueFactory<>("userScore"));
        tableAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        tableGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        tableLanguage.setCellValueFactory(new PropertyValueFactory<>("language"));
    }


}
