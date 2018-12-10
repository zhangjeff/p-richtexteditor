package com.jeff.template.web.upload;

import java.io.Serializable;


public class FileUploadReturn implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer code;

    private Value value;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public static class Value implements Serializable {

        private static final long serialVersionUID = 1L;

        private String fileUrl;
        private Integer width;
        private Integer height;

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

    }
}
