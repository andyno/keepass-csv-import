package csv;

import com.opencsv.CSVReader;
import org.linguafranca.pwdb.Entry;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVImporter {

    private List<CSVEntry> entries;

    public CSVImporter(File csvFile, Order order) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(csvFile));
        entries = new ArrayList<CSVEntry>();
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            entries.add(createEntry(nextLine, order));
        }
    }

    public List<CSVEntry> getEntries() {
        return entries;
    }

    private CSVEntry createEntry(String[] line, Order order) {
        CSVEntry entry = new CSVEntry();
        int notesOrder = order.getNotesOrder();
        int groupOrder = order.getGroupOrder();
        entry.setTitle(line[order.getTitleOrder()]);
        entry.setPassword(line[order.getPasswordOrder()]);
        entry.setUsername(line[order.getUsernameOrder()]);
        entry.setUrl(line[order.getUrlOrder()]);
        if (notesOrder >= 0) {
            entry.setNotes(line[notesOrder]);
        }
        if (groupOrder >= 0) {
            entry.setGroup(line[order.getGroupOrder()]);
        }
        return entry;
    }

    public static class Order {
        public static final String GROUP = "Group";
        private Map<String, Integer> order;

        public Order() {
            order = new HashMap<String, Integer>();
        }

        public Integer getTitleOrder() {
            return order.get(Entry.STANDARD_PROPERTY_NAME_TITLE);
        }

        public Integer getUsernameOrder() {
            return order.get(Entry.STANDARD_PROPERTY_NAME_USER_NAME);
        }

        public Integer getPasswordOrder() {
            return order.get(Entry.STANDARD_PROPERTY_NAME_PASSWORD);
        }

        public Integer getUrlOrder() {
            return order.get(Entry.STANDARD_PROPERTY_NAME_URL);
        }

        public Integer getNotesOrder() {
            return order.get(Entry.STANDARD_PROPERTY_NAME_NOTES);
        }

        public Integer getGroupOrder() {
            return order.get(GROUP);
        }

        public void setTitleOrder(Integer index) {
            order.put(Entry.STANDARD_PROPERTY_NAME_TITLE, index);
        }

        public void setUsernameOrder(Integer index) {
            order.put(Entry.STANDARD_PROPERTY_NAME_USER_NAME, index);
        }

        public void setPasswordOrder(Integer index) {
            order.put(Entry.STANDARD_PROPERTY_NAME_PASSWORD, index);
        }

        public void setUrlOrder(Integer index) {
            order.put(Entry.STANDARD_PROPERTY_NAME_URL, index);
        }

        public void setNotesOrder(Integer index) {
            order.put(Entry.STANDARD_PROPERTY_NAME_NOTES, index);
        }

        public void setGroupOrder(Integer index) {
            order.put(GROUP, index);
        }
    }
}
