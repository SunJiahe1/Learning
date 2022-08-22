package com.example.tradingPlatform.service;

import com.example.tradingPlatform.dao.TradingDataMapper;
import com.example.tradingPlatform.pojo.MultipleChoice;
import com.example.tradingPlatform.pojo.OfficialMarket;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Service
public class AdministratorService {
    @Autowired
    TradingDataMapper tradingDataMapper;

    public String getAdName() {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        String user_name = SecurityContextHolder.getContext().getAuthentication().getName();

        json.accumulate("ad_name", user_name);
        array.add(json);

        return array.toString();
    }

    public String addOfficialMarket(String ticket_name, int ticket_price, int ticket_amount, String seller) {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        int result = tradingDataMapper.addOfficialMarket(new OfficialMarket(ticket_name, ticket_price, ticket_amount, seller));

        if (result <= 0) {
            json.accumulate("index","false");
        }else {
            json.accumulate("index","ok");
        }

        array.add(json);

        return array.toString();
    }

    public String addImg(MultipartFile photo) throws IOException {
        ModelAndView mv = new ModelAndView();
        //判断用户是否上传了文件
        if(!photo.isEmpty()){

            //文件上传的地址
            String path = ResourceUtils.getURL("classpath:").getPath()+"static/upload";
            String realPath = path.replace('/', '\\').substring(1,path.length());
            //用于查看路径是否正确
            System.out.println(realPath);

            //获取文件的名称
            final String fileName = photo.getOriginalFilename();

            //限制文件上传的类型
            String contentType = photo.getContentType();
            if("image/jpeg".equals(contentType) || "image/jpg".equals(contentType) ){
                File file = new File(realPath,fileName);
                //完成文件的上传
                photo.transferTo(file);
                System.out.println("图片上传成功!");
                String path01 = "../upload/"+fileName;
                mv.addObject("path" ,path01);
                mv.setViewName("lookphoto");
                return "图片上传成功!";
            } else {
                System.out.println("上传失败！");
                mv.setViewName("upload");
                return "上传失败！";
            }
        } else {
            System.out.println("上传失败！");
            mv.setViewName("upload");
            return "上传失败！";
        }
    }

//    public String getImg(String imgName) throws Exception {
//        JSONObject json = new JSONObject();
//        JSONArray array = new JSONArray();
//
//        String path = ResourceUtils.getURL("classpath:").getPath()+"static/upload/";
//        String realPath = path.replace('/', '\\').substring(1,path.length()) + imgName + ".jpg";
//        System.out.println(realPath);
//
//        json.accumulate("path", realPath);
//        array.add(json);
//
//        return array.toString();
//    }

    public String dropOfficialMarket(String ticket_name) {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        int result = tradingDataMapper.deleteOfficialMarket(ticket_name);

        if (result <= 0) {
            json.accumulate("index","false");
        }else {
            json.accumulate("index","ok");
        }

        array.add(json);

        return array.toString();
    }

    public String addExam(int id, String title, String option_a, String option_b, String option_c, String option_d, String answer) {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();

        int result = tradingDataMapper.addExam(new MultipleChoice(id, title, option_a, option_b, option_c, option_d, answer));

        if (result <= 0) {
            json.accumulate("index","false");
        }else {
            json.accumulate("index","ok");
        }

        array.add(json);

        return array.toString();
    }
}
