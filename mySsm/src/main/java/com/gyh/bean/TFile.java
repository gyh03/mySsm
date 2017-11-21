package com.gyh.bean;

import java.util.Date;

public class TFile {
    private Integer id;

    private String orgiName;

    private String fileName;

    private String fileRealPath;

    private String fileReltPath;

    private String url;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrgiName() {
        return orgiName;
    }

    public void setOrgiName(String orgiName) {
        this.orgiName = orgiName == null ? null : orgiName.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFileRealPath() {
        return fileRealPath;
    }

    public void setFileRealPath(String fileRealPath) {
        this.fileRealPath = fileRealPath == null ? null : fileRealPath.trim();
    }

    public String getFileReltPath() {
        return fileReltPath;
    }

    public void setFileReltPath(String fileReltPath) {
        this.fileReltPath = fileReltPath == null ? null : fileReltPath.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}