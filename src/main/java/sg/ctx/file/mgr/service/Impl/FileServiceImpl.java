package sg.ctx.file.mgr.service.Impl;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sg.ctx.file.mgr.domain.FileEntity;
import sg.ctx.file.mgr.mapper.FileMapper;
import sg.ctx.file.mgr.service.FileService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileMapper fileMapper;

    @Value("${ctx.file-service.root.path}")
    private String rootPath;

    @Override
    public FileEntity saveFile(int bucketId, String fileName, InputStream inputStream, long fileSize) throws IOException {
        String ext = FilenameUtils.getExtension(fileName);
        String logicName = FilenameUtils.getBaseName(fileName);

        // 读取上传数据
        String physicalName = UUID.randomUUID().toString();
        String fullName = FilenameUtils.concat(this.rootPath, physicalName + "." + ext);
        // 保存上传数据到磁盘
        File file = new File(fullName);
        FileUtils.copyInputStreamToFile(inputStream, file);

        FileEntity fileEntity = new FileEntity();
        fileEntity.setBucketId(bucketId);
        fileEntity.setLogicName(logicName);
        fileEntity.setPhysicalName(physicalName);
        fileEntity.setLogicPath("/");
        fileEntity.setExt("pdf");
        fileEntity.setGmtCreate(new Date());
        fileEntity.setGmtModified(new Date());
        fileEntity.setFileSize(fileSize);
        fileEntity.setExt(ext);

        // 返回 File 实例
        this.fileMapper.insert(fileEntity);
        return fileEntity;
    }

    @Override
    public FileEntity getFile(int fileId) throws IOException {
        var fileEntity = this.fileMapper.selectById(fileId);

        String ext = fileEntity.getExt();
        String physicalName = fileEntity.getPhysicalName();
        String fullName = FilenameUtils.concat(this.rootPath, physicalName + "." + ext);
        File file = new File(fullName);

        var content = FileUtils.readFileToByteArray(file);
        fileEntity.setContent(content);
        return fileEntity;
    }
}
