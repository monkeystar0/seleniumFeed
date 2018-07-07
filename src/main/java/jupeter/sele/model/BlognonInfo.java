package jupeter.sele.model;

import org.joda.time.DateTime;

public class BlognonInfo {
    public int id;
    public String title;
    public String detail;
    public String dateTime;

    public BlognonInfo(int id, String title, String detail, String dateTime) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.dateTime = dateTime;
    }

    public BlognonInfo(int id, String title, String detail) {
        this.id = id;
        this.title = title;
        this.detail = detail;
    }

}
