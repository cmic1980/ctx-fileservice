package sg.ctx.file.mgr.domain;

import java.util.Date;

public class FileEntity {
    private int id;
    private int bucketId;
    private String physicalName;
    private String logicName;
    private String logicPath;
    private String ext;
    private long fileSize;
    private Date gmtCreate;
    private Date gmtModified;
    private byte[] content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBucketId() {
        return bucketId;
    }

    public void setBucketId(int bucketId) {
        this.bucketId = bucketId;
    }

    public String getPhysicalName() {
        return physicalName;
    }

    public void setPhysicalName(String physicalName) {
        this.physicalName = physicalName;
    }

    public String getLogicName() {
        return logicName;
    }

    public void setLogicName(String logicName) {
        this.logicName = logicName;
    }

    public String getLogicPath() {
        return logicPath;
    }

    public void setLogicPath(String logicPath) {
        this.logicPath = logicPath;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
