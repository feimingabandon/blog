package com.zou.mapper;

import com.zou.pojo.Blog;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper     // 告诉 MyBatis 这是个Mapper，然后就会匹配xml文件去实现里面的方法
@Repository // 对象交给spring管理。
public interface BlogMapper {

    // 插入一篇博客,添加
    Integer insertBlog(Blog blog);

    // 查询所有博客
    List<Blog> selectBlog(int paging);

    // 通过 id 查询博客
    Blog selectIdBlog(int id);

    // 修改博客博客
    Integer updateBlog(Blog blog);

    // 通过 id 删除博客
    Integer deleteBlog(int id);

    // 通过关键字查询博客
    List<Blog> selectKeyBlog(String key);

    // 查询用户有多少未审核文章
    List<Integer> selectUserAudit(int userId);

    // 查询未审核或者审核不通过文章
    List<Blog> queryNoPassBlog(@Param("paging")int paging, @Param("audit")int audit);

    // 修改文章是通过还是不通过
    Integer updateAudit(@Param("blogId")int blogId,@Param("audit")int audit);

}
