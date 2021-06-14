package com.misa.store.db;
import java.io.IOException;

public class DeleteMethod {
    public boolean deleteItem(int id) {
        String response = connectionForDelete(id);
        return response.equals("OK");
    }

    private String connectionForDelete(int id) {
        DataBaseProperties dbProp = new DataBaseProperties();
        String response = "";
        try {
            response = new Connect().delete(dbProp.get("url") + ":" + dbProp.get("port") + dbProp.get("path") + "/" + id);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException npe){
            throw new NullPointerException();
        }

        return response;
    }
}
