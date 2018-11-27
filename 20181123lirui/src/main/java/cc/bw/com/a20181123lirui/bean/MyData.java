package cc.bw.com.a20181123lirui.bean;

public class MyData {
    private String title;
    private String date;
    private String thumbnail_pic_s;

    public MyData(String title, String date, String thumbnail_pic_s) {
        this.title = title;
        this.date = date;
        this.thumbnail_pic_s = thumbnail_pic_s;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getThumbnail_pic_s() {
        return thumbnail_pic_s;
    }

    public void setThumbnail_pic_s(String thumbnail_pic_s) {
        this.thumbnail_pic_s = thumbnail_pic_s;
    }
}
