package com.jeff.template.web;
import com.jeff.template.web.upload.FileUploadReturn;
import com.jeff.template.model.base.User;
import com.jeff.template.service.IUserService;
import com.jeff.template.web.wang.WangEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;


@Controller
public class TemplateController {
    @Autowired
    private IUserService userService;

    @Value("${img_upload_url}")
    private String imgUploadUrl;

    @Value("${token}")
    private String token;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET ,value = "/user/search")
    public List search() {
        List<User> userList = userService.queryUserList();
        return userList;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST ,value = "/insertUser")
    public String createUser() {
        User user = new User();
        user.setUserId(1111);
        user.setUserName("张三");
        user.setCreateDate(new Date());
        int i =  userService.insert(user);
        return "insertUser " + i;
    }

    @ResponseBody
    @RequestMapping(value = "/uploadFilePost", method = RequestMethod.POST)
    public FileUploadReturn uploadFilePost(MultipartFile file, HttpServletRequest request) throws IOException {
        Resource resource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };

        MultiValueMap<String, Object> bodyParams = new LinkedMultiValueMap<>();
        bodyParams.add("file",  resource);
        bodyParams.add("appName", "dealer");
        bodyParams.add("token", token);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyParams, headers);
        RestTemplate restTemplate = new RestTemplate();
        FileUploadReturn fileUploadReturn = restTemplate.postForObject(imgUploadUrl, requestEntity, FileUploadReturn.class, String.class);
        return fileUploadReturn;
    }


    @ResponseBody
    @RequestMapping(value = "/uploadFileForEditor", method = RequestMethod.POST)
    public WangEditor uploadFileForEditor(MultipartFile file, HttpServletRequest request) throws IOException {
        Resource resource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };

        MultiValueMap<String, Object> bodyParams = new LinkedMultiValueMap<>();
        bodyParams.add("file",  resource);
        bodyParams.add("appName", "dealer");
        bodyParams.add("token", token);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyParams, headers);
        RestTemplate restTemplate = new RestTemplate();
        FileUploadReturn fileUploadReturn = restTemplate.postForObject(imgUploadUrl, requestEntity, FileUploadReturn.class, String.class);
        String [] str = {fileUploadReturn.getValue().getFileUrl()};
        WangEditor we = new WangEditor(str);
        return we;
    }

    @RequestMapping(value = "/upload",method=RequestMethod.POST)
    @ResponseBody
    public WangEditor uploadFile(
            @RequestParam("myFile") MultipartFile multipartFile,
            HttpServletRequest request) {

        try {
            Resource resource = new ByteArrayResource(multipartFile.getBytes()) {
                @Override
                public String getFilename() {
                    return multipartFile.getOriginalFilename();
                }
            };

            MultiValueMap<String, Object> bodyParams = new LinkedMultiValueMap<>();
            bodyParams.add("file",  resource);
            bodyParams.add("appName", "dealer");
            bodyParams.add("token", token);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyParams, headers);
            RestTemplate restTemplate = new RestTemplate();
            FileUploadReturn fileUploadReturn = restTemplate.postForObject(imgUploadUrl, requestEntity, FileUploadReturn.class, String.class);
            String url = fileUploadReturn.getValue().getFileUrl();
            String [] str = {url};
            WangEditor we = new WangEditor(str);
            return we;
        } catch (IOException e) {
            System.out.println("上传文件失败" + e.getMessage());
        }
        return null;

    }


    @RequestMapping(value = "/uploadmobile",method=RequestMethod.POST)
    @ResponseBody
    public String uploadFileMobile(
            @RequestParam("wangEditorMobileFile") MultipartFile multipartFile,
            HttpServletRequest request) {

        try {
            Resource resource = new ByteArrayResource(multipartFile.getBytes()) {
                @Override
                public String getFilename() {
                    return multipartFile.getOriginalFilename();
                }
            };

            MultiValueMap<String, Object> bodyParams = new LinkedMultiValueMap<>();
            bodyParams.add("file",  resource);
            bodyParams.add("appName", "dealer");
            bodyParams.add("token", token);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyParams, headers);
            RestTemplate restTemplate = new RestTemplate();
            FileUploadReturn fileUploadReturn = restTemplate.postForObject(imgUploadUrl, requestEntity, FileUploadReturn.class, String.class);
            String url = fileUploadReturn.getValue().getFileUrl();
//            String [] str = {url};
//            WangEditor we = new WangEditor(str);
            return url;
        } catch (IOException e) {
            System.out.println("上传文件失败" + e.getMessage());
        }
        return null;

    }
}
