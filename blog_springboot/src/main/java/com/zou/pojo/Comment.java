package com.zou.pojo;

public class Comment {

    private int id;         //评论id
    private int blogId;         //属于哪个博客
    private int level;         //1:表示父级为博客，2：表示是评论下的评论(即回复)
    private int level2;         //默认为-1，如果是二级评论则记录父级评论id
    private int userId;         //发布者id
    private String time;         //发布时间
    private String content;         //评论内容
    private String audit;         //审核状态（0(通过)、1(未通过)、2(未审核)默认通过
    private String like;         //点赞数

    public Comment() {
    }

    public Comment(int id, int blogId, int level, int level2, int userId, String time, String content, String audit, String like) {
        this.id = id;
        this.blogId = blogId;
        this.level = level;
        this.level2 = level2;
        this.userId = userId;
        this.time = time;
        this.content = content;
        this.audit = audit;
        this.like = like;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel2() {
        return level2;
    }

    public void setLevel2(int level2) {
        this.level2 = level2;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", blogId=" + blogId +
                ", level=" + level +
                ", level2=" + level2 +
                ", userId=" + userId +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                ", audit='" + audit + '\'' +
                ", like='" + like + '\'' +
                '}';
    }
}
