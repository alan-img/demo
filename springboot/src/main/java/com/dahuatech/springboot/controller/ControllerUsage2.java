package com.dahuatech.springboot.controller;

import com.dahuatech.springboot.bean.Student;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author alan
 * @QQ 738437340
 * @organization ahut.edu.cn
 * @create-time 2021-09-27 21:43
 * @description to do
 */

@Controller
public class ControllerUsage2 {

    /**
     * 通过方法形参注入HttpServletRequest对象向request域对象保存数据
     * 由于使用了Spring MVC框架，不推荐使用原生Servlet API向域对象保存数据
     * @param request
     * @return
     */
    @RequestMapping("/test1")
    public String useRawServletAPI(HttpServletRequest request, HttpSession httpSession,
                                   @CookieValue String JSESSIONID,
                                   @RequestHeader String host) {
        request.setAttribute("username", "request");
        httpSession.setAttribute("username", "session");

        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("username", "application");

        ServletContext servletContext1 = httpSession.getServletContext();
        servletContext1.setAttribute("username", "application1");

        System.out.println("JSESSIONID = " + JSESSIONID);
        System.out.println("host = " + host);

        String contextPath = servletContext.getContextPath();
        System.out.println("contextPath = " + contextPath);

        String realPath = servletContext.getRealPath("/");
        System.out.println("realPath = " + realPath);

        ServletContext context = servletContext.getContext("/");
        System.out.println("context = " + context);

        return "success";
    }

    /**
     * 通过返回ModelAndView对象向request域对象添加数据
     * @return
     */
    @RequestMapping("/test2")
    public ModelAndView test2() {
        // 创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();
        // 添加数据
        modelAndView.addObject("username", "test2");
        // 设置转发的视图名
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /**
     * 通过方法形参中注入mdoel对象向request域对象添加数据
     * @param model
     * @return
     */
    @RequestMapping("/test3")
    public String test3(Model model) {
        model.addAttribute("username", "test3");
        return "success";
    }

    /**
     * 通过方法形参中注入Map对象向request域对象添加数据
     * @param map
     * @return
     */
    @RequestMapping("/test4")
    public String test4(Map<String, Object> map) {
        map.put("username", "test4");
        return "success";
    }

    /**
     * 通过方法形参中注入ModelMap对象向request域对象添加数据
     * @param modelMap
     * @return
     */
    @RequestMapping("/test55")
    public String test5(ModelMap modelMap) {
        modelMap.addAttribute("username", "test5");
        return "success";
    }

    /*
    ------------------------------------------------------------
     */

    // 通过servlet原生API向session域中添加数据

    /**
     * SpringMVC可以直接向方法形参列表注入HttpSession对象，不能直接注入Cookie数组对象
     * 如果要获取Cookie数组对象，需要通过先在形参列表中注入HttpServletRequest对象，
     * 通过HttpServletRequest对象调用getCookies方法间接获取
     * @param session
     * @return
     */
    @RequestMapping("/test66")
    public String test6(HttpSession session) {
        session.setAttribute("username", "test6");
        return "success";
    }

    /**
     * 通过方法形参输入HttpSession对象，间接获取servletContext对象，进而向域中添加数据
     * @param session
     * @param request
     * @return
     */
    @RequestMapping("/test77")
    public String test7(HttpSession session, HttpServletRequest request) {
        /**
         * 通过请求request对象和httpsession对象都可以获取servletcontext对象
         */
//        ServletContext servletContext = request.getServletContext();
        ServletContext application = session.getServletContext();
        application.setAttribute("username", "test7");
        return "success";
    }

    /**
     * 请求转发
     * @return
     */
    @RequestMapping("/test88")
    public String test8() {
        /**
         * 转发只能转发到一个请求，不能转发到一个具体的页面，因为页面必须由ThymeLeafView视图解析器解析渲染后才能访问
         * 采用ThymeLeafView视图解析器后，通过"forward:/test7"这种方式默认创建的视图是InternalResourceView
         * 在通过创建ThymeLeafView视图解析渲染页面才能被访问
         *
         * 如果视图解析技术采用JSTL，则需要配置的视图解析器为ThymeLeafView
         */
        return "forward:/test7";
    }

    /**
     * 请求重定向
     * @return
     */
    @RequestMapping("/test99")
    public String test9() {
        /**
         * 重定向只能重定向到一个请求，不能重定向到一个具体的页面，因为页面必须由ThymeLeafView视图解析器解析渲染后才能访问
         * 采用ThymeLeafView视图解析器后，通过"redirect:/test7"这种方式默认创建的视图是RedirectView
         * 在通过创建ThymeLeafView视图解析渲染页面才能被访问
         *
         * 如果视图解析技术采用JSTL，则需要配置的视图解析器为ThymeLeafView
         */
        return "redirect:/test7";
    }

    @RequestMapping("test100")
    private String test10() {
        return "success";
    }

    /*
    使用RESTful风格对数据进行增删改查操作
     */

    /**
     * 查询所有用户
     * @return
     */
    @GetMapping("/user")
    public String get(Model model) {
        return "success";
    }

    /**
     * 查询给定id的用户
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    public String get(@PathVariable("id") Integer id) {
        return "success";
    }

    @GetMapping("/toAdd")
    public String toAdd() {
        return "add";
    }

    /**
     * 添加用户
     * @return
     */
    @PostMapping("/user")
    public String post(Student student) {
        return "redirect:/user";
    }

    /**
     * 删除给定id的用户
     * @param id
     * @return
     */
    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable("id") Integer id) {
        System.out.println("id = " + id);
        return "redirect:/user";
    }

    @GetMapping("/toUpdate/{id}")
    public String toUpdate(@PathVariable("id") Integer id, Model model) {
        return "update";
    }

    /**
     * 修改用户
     * @return
     */
    @PutMapping("/user")
    public String put(Student student) {
        return "redirect:/user";
    }

    /**
     * 通过方法形参注解获取请求体
     * @param requestBody
     * @return
     */
    @RequestMapping("/test111")
    public String test11(@RequestBody String requestBody) {
        System.out.println("requestBody = " + requestBody);
        return "flag";
    }

    /**
     * ，请求实体中包含所有请求头和请求体的内容
     * @param requestEntity
     * @return
     */
    @RequestMapping("/test122")
    public String test12(RequestEntity<String> requestEntity) {
        HttpHeaders headers = requestEntity.getHeaders();
        String body = requestEntity.getBody();
        System.out.println("headers = " + headers);
        System.out.println("body = " + body);
        return "flag";
    }

    /**
     * 通过在控制器方法注解将返回值作为响应体放回
     * @return
     */
    @RequestMapping("/test133")
    @ResponseBody
    public String test13() {
        return "hello, spring mvc";
    }

    /**
     * 设置自动将JavaBean转换为json字符串并作为响应体返回
     * @return
     */
    @RequestMapping("/test144")
    @ResponseBody
    public Student test14() {
        Student student = new Student("alan", 23);
        return student;
    }

    /**
     * Spring MVC接收由axios发送的ajax请求
     * @RestController注解添加在控制器方法所在的类上
     * 表示为控制器类添加@Controller注解和控制器方法中的类添加@ResponseBody注解
     * @return
     */
    @RequestMapping("/ajax")
    @ResponseBody
    public String ajax(Student student) {
        System.out.println("student = " + student);
        return "success";
    }

    /**
     * ResponseEntity用于控制器方法的返回值类型, 该返回值就是相应到浏览器的相应报文
     * 通过ResponseEntity实现文件下载操作
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/test155")
    public ResponseEntity<byte[]> test15(HttpSession session) throws Exception {
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("/static/img/1.jpg");
        System.out.println(realPath);
        FileInputStream fis = new FileInputStream(new File(realPath));
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);
        fis.close();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=1.jpg");
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }


    /**
     * 实现文件上传
     * 通过控制器方法形参MultipartFile实现上传文件
     *
     * @param file
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping("/test166")
    public String test16(MultipartFile file, HttpSession session) throws IOException {
        // 获取原始上传文件的文件名
        String filename = file.getOriginalFilename();
        System.out.println("filename = " + filename);
        // 获取文件名后缀
        String suffix = filename.substring(filename.lastIndexOf("."));
        System.out.println("suffix = " + suffix);
        // 服务器保存文件的文件名
        filename = UUID.randomUUID().toString() + suffix;
        // 获取上下文对象
        ServletContext application = session.getServletContext();
        /**
         * 获取服务器上的绝对路径
         * 如果这个目录不存在也会将路径拼接上工程的部署路径
         */
        String realPath = application.getRealPath("/upload");
        System.out.println("realPath = " + realPath);
        /**
         * 根据路径创建目录文件，这个目录存在在内存中
         * 在真实机器上可以不存在
         */
        File file1 = new File(realPath);
        // 目录不存在则创建
        if (!file1.exists()) {
            file1.mkdir();
        }
        file.transferTo(new File(realPath + File.separator + filename));
        return "success";
    }

    @RequestMapping("/test177")
    public String test17() {
        // 模拟算术异常
        int val = 10 / 0;
        return "success";
    }

    @RequestMapping("/test188")
    public String test18() {
        String s = null;
        // 模拟空指针异常
        s.indexOf("a");
        return "success";
    }

}
