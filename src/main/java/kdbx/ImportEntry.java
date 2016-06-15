package kdbx;

import csv.CSVEntry;
import helpers.GroupFinder;
import org.linguafranca.pwdb.Database;
import org.linguafranca.pwdb.Entry;
import org.linguafranca.pwdb.Group;

public class ImportEntry {

    private static final int FOLDER_ICON = 48;
    private static final int ENTRY_ICON = 0;

    public static void create(Database database, CSVEntry csvEntry) {
        Group group = GroupFinder.find(database, csvEntry.getGroup());
        if (group == null && !csvEntry.getGroup().isEmpty()) {
            group = createGroup(database, csvEntry.getGroup());
        } else if (group == null) {
            group = database.getRootGroup();
        }
        group.addEntry(createEntry(database, csvEntry));
    }

    private static Group createGroup(Database database, String name) {
        Group newGroup = database.newGroup();
        newGroup.setName(name);
        newGroup.setIcon(database.newIcon(FOLDER_ICON));
        database.getRootGroup().addGroup(newGroup);
        return newGroup;
    }

    private static Entry createEntry(Database database, CSVEntry csvEntry) {
        Entry newEntry = database.newEntry();
        newEntry.setTitle(csvEntry.getTitle());
        newEntry.setNotes(csvEntry.getNotes());
        newEntry.setUrl(csvEntry.getUrl());
        newEntry.setPassword(csvEntry.getPassword());
        newEntry.setUsername(csvEntry.getUsername());
        newEntry.setIcon(database.newIcon(ENTRY_ICON));
        return newEntry;
    }
}
