package csv;

public class CSVEntry {
    private String title;
    private String url;
    private String username;
    private String password;
    private String notes;
    private String group;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String toString() {
        String note = notes;
        if (note.length() > 10) {
            note = note.substring(0, 10) + "...";
        }
        return "Entry[title:"+title+",username:"+username+",password:"+password+",url:"+url+",notes:"+note+",group:"+group+"]";
    }
}
