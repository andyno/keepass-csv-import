package config;

import csv.CSVImporter;
import org.linguafranca.pwdb.Database;
import org.linguafranca.pwdb.kdbx.KdbxCredentials;
import org.linguafranca.pwdb.kdbx.dom.DomDatabaseWrapper;
import org.linguafranca.security.Credentials;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class ConfigFactory {

    private final String database;
    private final String keyfile;
    private final String output;
    private final String csvfile;
    private final String order;
    private final byte[] password;
    private final boolean dryRun;

    public ConfigFactory(Properties properties, byte[] password) {
        this(properties, password, false);
    }

    public ConfigFactory(Properties properties, byte[] password, boolean dryRun) {
        this.database = properties.getProperty("database");
        this.keyfile = properties.getProperty("keyfile");
        this.csvfile = properties.getProperty("csvfile");
        this.output = properties.getProperty("output");
        this.order = properties.getProperty("order");
        this.password = password;
        this.dryRun = dryRun;
    }

    private File getOutputFile() {
        if (output == null || output.isEmpty()) {
            return new File(database);
        } else {
            return new File(output);
        }
    }

    public void writeOutputFile(Database database) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(getOutputFile());
        DomDatabaseWrapper domDatabaseWrapper = (DomDatabaseWrapper)database;
        domDatabaseWrapper.save(getCredentials(), outputStream);
    }

    public Database getDatabase() throws IOException {
        if (this.database != null && !this.database.isEmpty()) {
            File databaseFile = new File(this.database);
            return DomDatabaseWrapper.load(getCredentials(), new FileInputStream(databaseFile));
        } else {
            return new DomDatabaseWrapper();
        }
    }

    public Credentials getCredentials() throws IOException {
        if (keyfile != null && !keyfile.isEmpty() && password != null) {
            return new KdbxCredentials.KeyFile(password, new FileInputStream(keyfile));
        } if (password != null) {
            return new KdbxCredentials.Password(password);
        } else {
            return null;
        }
    }

    public CSVImporter getCSVImporter() throws IOException {
        return new CSVImporter(new File(csvfile), getOrder());
    }

    public boolean isDryRun() {
        return dryRun;
    }

    public CSVImporter.Order getOrder() {
        CSVImporter.Order order = new CSVImporter.Order();

        String[] input = this.order.split(",");

        List<String> orderList = Arrays.asList(input);

        int url = orderList.indexOf("url");
        int username = orderList.indexOf("username");
        int password = orderList.indexOf("password");
        int notes = orderList.indexOf("notes");
        int title = orderList.indexOf("title");
        int group = orderList.indexOf("group");

        order.setUrlOrder(url);
        order.setUsernameOrder(username);
        order.setPasswordOrder(password);
        order.setNotesOrder(notes);
        order.setTitleOrder(title);
        order.setGroupOrder(group);

        return order;
    }
}
