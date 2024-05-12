package com.dahuatech.springboot.controller;

import com.dahuatech.springboot.bean.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * @author alan
 * @QQ 738437340
 * @organization ahut.edu.cn
 * @create-time 2021-09-27 12:50
 * @description to do
 */

@Controller
public class ControllerUsage1 {

    @RequestMapping("/")
    public String root() {
        return "index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/target")
    public String target() {
        return "target";
    }

    @GetMapping("/test1")
    public String test1() {
        return "test1";
    }

    @RequestMapping(value = "/test2", params = "username!=alan", method = RequestMethod.GET)
    public String test2() {
        return "test2";
    }


    /**
     * "?"表示任意单个字符
     * "*"表示任意0个或多个字符
     * "**"表示任意0层或多层目录
     */
    @RequestMapping(value = "/**/te*t3/?", params = "!username")
    public String test3() {
        return "test3";
    }

    // 路径变量
    @RequestMapping("/test4/{id}/{username}")
    public String test4(@PathVariable("id") Integer id,
                        @PathVariable("username") String username) {
        System.out.println("id = " + id);
        System.out.println("username = " + username);
        return "test4";
    }

    @RequestMapping("/test5")
    public String test5() {
        return "success";
    }

    // 通过servlet原生api获取请求参数
    @RequestMapping("/test6")
    public String test6(HttpServletRequest request,
                        HttpServletResponse response) {
        String contextPath = request.getContextPath();
        System.out.println("contextPath = " + contextPath);

        System.out.println("request = " + request);

        HttpSession session = request.getSession();
        session.getServletContext();
        System.out.println("session = " + session);

        ServletContext servletContext = request.getServletContext();
        System.out.println("servletContext = " + servletContext);

        System.out.println("session.isNew() = " + session.isNew());
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username = " + username);
        System.out.println("password = " + password);

        return "success";
    }

    // 通过spring mvc获取请求参数，直接通过形参名接收请求参数
    @RequestMapping("/test7")
    public String test7(String username, String password, String[] hobby) {
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println("hobby = " + Arrays.toString(hobby));

        return "success";
    }

    // 处理请求参数和映射方法形参不同名的问题

    /**
     * @RequestParam注解用法:

     *
     * @param name
     * @param password
     * @param hobby
     * @return
     */
    @RequestMapping("/test8")
    public String test8(@RequestParam(value = "username", required = false, defaultValue = "default-value") String name, String password, String[] hobby) {
        System.out.println("name = " + name);
        System.out.println("password = " + password);
        System.out.println("hobby = " + Arrays.toString(hobby));
        return "success";
    }

    /**
     * 通过RequestHeader和CookieValue注解，直接获取每次请求的请求头和cookie的key对应的值
     * @param host
     * @param JSESSIONID
     * @return
     */
    @RequestMapping("/test9")
    public String test9(
            @RequestHeader String host,
            @RequestBody String requestBody,
            @CookieValue String JSESSIONID) {
        System.out.println("host = " + host);
        System.out.println("requestBody = " + requestBody);
        System.out.println("JSESSIONID = " + JSESSIONID);
        return "success";
    }

    // 自动封装Java Bean
    @RequestMapping("/test10")
    public String test10(Student student) {
        System.out.println("user = " + student);
        return "success";
    }

    @RequestMapping("/test11")
    public String test11(HttpServletRequest request, HttpServletResponse response) {
        // 为了测试，向通知浏览器器保存cookie数据
//        for (int i = 0; i < 3; i++) {
//            Cookie cookie = new Cookie("10" + i, "1000" + i);
//            response.addCookie(cookie);
//        }

        // 通过设置每个对象的cookie存活时间，间接删除浏览器中保存的cookie数据
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + " -> " + cookie.getValue());
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

        return "success";
    }

    // 测试字符编码
    @RequestMapping("/test12")
    public void test12(HttpServletRequest request, HttpServletResponse response) {
        String characterEncoding = request.getCharacterEncoding();
        String characterEncoding1 = response.getCharacterEncoding();
        System.out.println("characterEncoding = " + characterEncoding);
        System.out.println("characterEncoding1 = " + characterEncoding1);
    }

}
