package com.hncboy.tmall.controller;

import com.hncboy.tmall.pojo.Category;
import com.hncboy.tmall.service.CategoryService;
import com.hncboy.tmall.util.ImageUtil;
import com.hncboy.tmall.util.Page;
import com.hncboy.tmall.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("admin_category_list")
    public String list(Model model, Page page) {
        //获取当前页的分类集合
        List<Category> cs = categoryService.list(page);
        //获取分类总数
        int total = categoryService.total();
        //为分页对象设置总数
        page.setTotal(total);
        model.addAttribute("cs", cs);
        model.addAttribute("page", page);
        return "admin/listCategory";
    }

    @RequestMapping("admin_category_add")
    public String add(Category c, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {
        categoryService.add(c);
        //获取当前应用的路径
        File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder, c.getId() + ".jpg");
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        //接受上传的图片
        uploadedImageFile.getImage().transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
        return "redirect:/admin_category_list";
    }

    @RequestMapping("admin_category_delete")
    public String delete(int id, HttpSession session) {
        categoryService.delete(id);
        File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder, id + ".jpg");
        file.delete();
        return "redirect:/admin_category_list";
    }

    @RequestMapping("admin_category_edit")
    public String edit(int id, Model model) {
        Category c = categoryService.get(id);
        model.addAttribute("c", c);
        return "admin/editCategory";
    }

    @RequestMapping("admin_category_update")
    public String update(Category c, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {
        categoryService.update(c);
        MultipartFile image = uploadedImageFile.getImage();
        //判断是否有图片上传
        if (null != image && !image.isEmpty()) {
            File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
            File file = new File(imageFolder, c.getId() + ".jpg");
            image.transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);
        }
        return "redirect:/admin_category_list";
    }
}
