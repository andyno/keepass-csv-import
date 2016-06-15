package helpers;

import org.linguafranca.pwdb.Database;
import org.linguafranca.pwdb.Entry;
import org.linguafranca.pwdb.Group;
import org.linguafranca.pwdb.Visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabasePrinter {
    public static void print(Database database) {
        final Map<String, List<String>> directories = new HashMap<String, List<String>>();
        database.visit(new Visitor() {
            public void startVisit(Group group) {
            }

            public void endVisit(Group group) {

            }

            public void visit(Entry entry) {
                if (directories.get(entry.getParent().getName()) == null) {
                    directories.put(entry.getParent().getName(), new ArrayList<String>());
                }
                directories.get(entry.getParent().getName()).add(entry.getTitle());
            }

            public boolean isEntriesFirst() {
                return false;
            }
        });
        for (String key : directories.keySet()) {
            System.out.println(key);
            for (String s : directories.get(key)) {
                System.out.println(" - " + s);
            }
            System.out.println("");
        }
        System.out.println("-----------------------------------");
    }
}
