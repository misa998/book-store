package com.misa.store.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DataBaseProperties {
    private String LOCATION = "resources/config.properties";

    FileInputStream inputStream;
    Properties properties;

    public void getProperties() throws IOException {
        try{
            loadFile();
        } catch (NullPointerException npe){
            npe.printStackTrace();
        }
        catch (FileNotFoundException fnfe){
            System.out.println("Config file not found");
        } catch (IOException ioe){
            System.out.println("Error while reading configuration file.");
        } finally {
            if(inputStream != null)
                inputStream.close();
        }
    }

    private void loadFile() throws IOException {
        inputStream = new FileInputStream(LOCATION);
        properties = new Properties();
        properties.load(inputStream);
    }

    public DataBaseProperties() {
        try {
            getProperties();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public String get(String name){
        return properties.getProperty(name);
    }

}
