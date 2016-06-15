import config.ConfigFactory;
import csv.CSVEntry;
import csv.CSVImporter;
import helpers.DatabasePrinter;
import kdbx.ImportEntry;
import org.linguafranca.pwdb.Database;
import org.linguafranca.pwdb.Entry;
import org.linguafranca.pwdb.Group;
import org.linguafranca.pwdb.Visitor;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("_  _ ____ ____ ___  ____ ____ ____");
        System.out.println("|_/  |___ |___ |__] |__| [__  [__ ");
        System.out.println("| \\_ |___ |___ |    |  | ___] ___]");
        System.out.println("\n        CSV IMPORTER v.1");
        System.out.println("-----------------------------------");
        ConfigFactory configFactory = loadConfig(args);
        Database database = configFactory.getDatabase();

        CSVImporter csvImporter = configFactory.getCSVImporter();
        List<CSVEntry> entryList = csvImporter.getEntries();

        for (CSVEntry entry : entryList) {
            ImportEntry.create(database, entry);
        }

        DatabasePrinter.print(database);

        if (!configFactory.isDryRun()) {
            configFactory.writeOutputFile(database);
        }
    }

    private static ConfigFactory loadConfig(String[] args) throws IOException {
        String password = promptPassword();
        System.out.println("-----------------------------------");
        System.out.println("Loading configuration...");
        Properties properties = new Properties();
        properties.load(new FileReader("config.properties"));
        if (args.length > 0 && args[0].equals("--dry-run")) {
            System.out.println("Dry run...\n");

            return new ConfigFactory(properties, password.getBytes(), true);
        } else {
            System.out.println();
            return new ConfigFactory(properties, password.getBytes());
        }
    }

    private static String promptPassword() {
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter password:\n");
        return in.nextLine();
    }
}
