package helpers;

import org.linguafranca.pwdb.Database;
import org.linguafranca.pwdb.Entry;
import org.linguafranca.pwdb.Group;
import org.linguafranca.pwdb.Visitor;

public class GroupFinder {
    public static Group find(final Database database, final String name) {
        final Group[] found = new Group[1];
        database.visit(new Visitor() {
            public void startVisit(Group group) {
                if (!group.getName().isEmpty() && group.getName().equalsIgnoreCase(name)) {
                    found[0] = group;
                }
            }

            public void endVisit(Group group) {

            }

            public void visit(Entry entry) {

            }

            public boolean isEntriesFirst() {
                return false;
            }
        });
        return found[0];
    }
}
