package com.zou.pojo;

public class Blog {

    private int id;              // 文章id
    private String title;        // 文章标题
    private String summary;      // 摘要
    private String content;      // 文章全文
    private int userId;         // 哪个用户的文章
    private int audit;           // 审核状态:0(通过)、1(未通过)、2(未审核)默认不通过
    private int like;            // 点赞数，可多次点赞
    private String releaseTime; // 发布时间
    private String mtime;        // 修改时间
    private int browse;          // 浏览量
    private int top;             // 0：置顶，1：不置顶
    private String tag;          // 文章标签，逗号分隔

    public Blog() {
    }

    public Blog(int id, String title, String summary, String content, int userId, int audit, int like, String releaseTime, String mtime, int browse, int top, String tag) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.userId = userId;
        this.audit = audit;
        this.like = like;
        this.releaseTime = releaseTime;
        this.mtime = mtime;
        this.browse = browse;
        this.top = top;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAudit() {
        return audit;
    }

    public void setAudit(int audit) {
        this.audit = audit;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    public int getBrowse() {
        return browse;
    }

    public void setBrowse(int browse) {
        this.browse = browse;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                ", audit=" + audit +
                ", like=" + like +
                ", releaseTime='" + releaseTime + '\'' +
                ", mtime='" + mtime + '\'' +
                ", browse=" + browse +
                ", top=" + top +
                ", tag='" + tag + '\'' +
                '}';
    }
}
