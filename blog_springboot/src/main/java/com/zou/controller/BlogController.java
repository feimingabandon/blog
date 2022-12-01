package com.zou.controller;


import com.alibaba.fastjson.JSONObject;
import com.zou.tool.JsonData;
import com.zou.tool.State;
import com.zou.pojo.Blog;
import com.zou.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

// 控制层进行传输
@RestController//使用的效果是将方法返回的对象直接在浏览器上展示成json格式.
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    BlogService blogService;

    JSONObject jsonObject = new JSONObject();

    // 插入一篇博客,添加  , HttpServletRequest request
    @PostMapping("/addBlog/{root}")
    public JSONObject insertBlog(@RequestBody  Blog blog,@PathVariable int root) {

        // 判断是不是普通用户，普通用户只能有三条未审核的记录
        if (root!=0){
            // 说明不是管理员，就查查此id有多少个未审核的文章
            List<Integer> integers = blogService.selectUserAudit(blog.getUserId());
            // 如果大于等于3条，我们就不让添加了
            // 先确保有数据
            if (!integers.isEmpty()){
                // 再看看是不是大于等于3条
                if (integers.size()>=3){
                    // 直接返回
                    return JsonData.json(jsonObject,"大哥，别搞啊，已经有"+integers.size()+"条没审核了，求放过！",null,State.fail);
                }
            }
        }
        // 正文不能为空
        if(blog.getContent()==null && blog.getSummary()==null)
            return JsonData.json(jsonObject,"唉？是不是有东西没写！",null,State.fail);
        // 权限验证
        if (root!=1 && root!=0)
            return JsonData.json(jsonObject,"大哥还挣扎呢？你没有权限啊！",null,State.root);
        // 调用插入数据库的方法
        Integer i = blogService.insertBlog(blog);
        if (i==1) {
            // 存储成功后发送邮件提醒
            blogService.sendMailBlog("又有文章需要审核啦！","用户id为：\n"+blog.getUserId()+"\n文章标题为：\n"+blog.getSummary()+"\n文章内容为：\n"+blog.getContent(),"1160653906@qq.com");

            return JsonData.json(jsonObject, "哎哟，存储成功啦！已发送邮件给管理大大了。", null, State.success);
        }
        return JsonData.json(jsonObject,"妈的！有毒！存储失败了！",blog.getTitle(),State.fail);
    }

    // 查询所有博客
    @GetMapping("/queryBlog/{paging}")
    public JSONObject selectBlog(@PathVariable int paging) {

        //因为我们传递的paging是页数，真正要查询的起始位置是(（页数-1）*10)+1，所以我们传递的参数要改一下
        // 查询数据库并接收数据
        List<Blog> blogs = blogService.selectBlog((paging-1)*10);

        // 生成json数据并返回
        // 放入数据：
        return JsonData.json(jsonObject,"查询成功！共有"+blogs.size()+"条数据",blogs,State.success);

    }

    // 查询未审核或者审核不通过的博客、audit：通过(0),不通过(1),未审核(2)
    @GetMapping("/queryNoPassBlog/{audit}/{paging}")
    public JSONObject queryNoPassBlog(@PathVariable int paging,@PathVariable int audit) {

        //因为我们传递的paging是页数，真正要查询的起始位置是((页数-1)*10)，所以我们传递的参数要改一下
        // 查询数据库并接收数据
        List<Blog> blogs = blogService.queryNoPassBlog((paging-1)*10,audit);

        // 生成json数据并返回
        // 放入数据：
        return JsonData.json(jsonObject,"查询成功！共有"+blogs.size()+"条数据",blogs,State.success);

    }

    // 通过 id 查询博客
    @GetMapping("/selectIdBlog/{id}") // 这个参数是 required 确定在 API 中的参数中是否必须要输出参数。
    //@RequestParam(value = "id",required = true) int id
    public JSONObject selectIdBlog(@PathVariable int id) {

        // 如果，没有返回值就是null
        Blog blog = blogService.selectIdBlog(id);
        if (blog!=null)
            return JsonData.json(jsonObject,"查询成功!",blog,State.success);

        return JsonData.json(jsonObject,"抱歉！无此博客",null,State.fail);
    }

    // 修改博客博客

    public int updateBlog(Blog blog) {
        return 0;
    }

    // 通过关键字查询
    @GetMapping("/selectKeyBlog/{key}")
    public JSONObject selectKeyBlog(@PathVariable String key) {

        List<Blog> blogs = blogService.selectKeyBlog(key);

        if (blogs.isEmpty()) // 如果是空的
            return JsonData.json(jsonObject,"没有与之相近的文章！","",State.fail);

        return JsonData.json(jsonObject,"查询成功！",blogs,State.success);
    }

    // 通过 id 删除博客
    @GetMapping("/deleteIdBlog/{id}/{root}/{userId}")
    public JSONObject deleteBlog(@PathVariable("id")int id,@PathVariable int root,@PathVariable int userId) {

        // 先进行权限认证
        // 游客
        if (root==2) return JsonData.json(jsonObject,"游客大哥，咋还想着直捣黄龙捏",null,State.root);

        // 删除的是自己文章或者是管理员
        // 先通过id查询属于哪个用户
        Blog blog = blogService.selectIdBlog(id);
        if (blog.getUserId()==userId || userId==1 ){
            // 进行删除
            Integer i = blogService.deleteBlog(id);
            if(i==null)
                return JsonData.json(jsonObject,"删除失败了。。。",null,State.fail);

            return JsonData.json(jsonObject,"啊偶，删除了！",null,State.success);
        }else
            return JsonData.json(jsonObject,"有没有一种可能，你没有权限",null,State.root);

    }

    // 通过 文章id和审核结果audit 去修改博客的审核状态
    @GetMapping("/deleteIdBlog/{blogId}/{audit}")
    public JSONObject updateAudit(@PathVariable int blogId,@PathVariable int audit) {

        // 不论最终是否对数据进行了修改，只要某条记录符合匹配条件，返回值就加1
        Integer integer = blogService.updateAudit(blogId, audit);
        if (integer!=1)
            return JsonData.json(jsonObject,"修改失败!",integer,State.fail);
        return JsonData.json(jsonObject,"修改成功!",integer,State.success);
    }


}
