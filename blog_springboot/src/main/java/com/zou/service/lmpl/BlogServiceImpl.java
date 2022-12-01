package com.zou.service.lmpl;

import com.zou.mapper.BlogMapper;
import com.zou.pojo.Blog;
import com.zou.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    JavaMailSenderImpl mailSender; // 发送邮件用到的类

    // 可以发送给
    public void sendMailBlog(String title,String text,String to){

        // 参数依次是：邮件标题、邮件内容、发给谁（邮箱地址）
        // 暂时from(来自谁只能是我自己，因为要授权码)
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(title);
        message.setText(text);

        message.setTo(to);
        message.setFrom("1160653906@qq.com");
        mailSender.send(message);
    }

    // 插入一篇博客,添加
    @Override
    public Integer insertBlog(Blog blog) {
        return blogMapper.insertBlog(blog);
    }

    // 查询所有博客
    @Override
    public List<Blog> selectBlog(int paging) {

        return blogMapper.selectBlog(paging);
    }

    // 通过 id 查询博客
    @Override
    public Blog selectIdBlog(int id) {

        return blogMapper.selectIdBlog(id);
    }

    // 修改博客博客
    @Override
    public int updateBlog(Blog blog) {
        return 0;
    }

    // 通过 id 删除博客
    @Override
    public Integer deleteBlog(int id) {
        return blogMapper.deleteBlog(id);
    }

    // 关键字查询
    @Override
    public List<Blog> selectKeyBlog(String key) {
        return blogMapper.selectKeyBlog(key);
    }

    @Override
    public List<Integer> selectUserAudit(int userId) {
        return blogMapper.selectUserAudit(userId);
    }

    @Override
    public List<Blog> queryNoPassBlog(int paging, int audit) {
        return blogMapper.queryNoPassBlog(paging,audit);
    }

    @Override
    public Integer updateAudit(int blogId, int audit) {
        return blogMapper.updateAudit(blogId,audit);
    }


}
