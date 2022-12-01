package com.zou.service;

import com.zou.pojo.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface BlogService {

    // 插入一篇博客,添加
    Integer insertBlog(Blog blog);

    // 查询所有博客
    List<Blog> selectBlog(int paging);

    // 通过 id 查询博客
    Blog selectIdBlog(int id);

    // 修改博客博客
    int updateBlog(Blog blog);

    // 通过 id 删除博客
    Integer deleteBlog(int id);

    // 通过关键字查询博客
    List<Blog> selectKeyBlog(String key);

    // 查询用户有多少未审核文章
    List<Integer> selectUserAudit(int userId);

    // 发送邮件的方法
    void sendMailBlog(String title,String text,String to);

    // 查询未审核或者审核不通过文章
    List<Blog> queryNoPassBlog(int paging, int audit);

    // 修改文章是通过还是不通过
    Integer updateAudit(int blogId,int audit);

}
