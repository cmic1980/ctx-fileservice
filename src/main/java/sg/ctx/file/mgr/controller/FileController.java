package sg.ctx.file.mgr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sg.ctx.file.mgr.domain.FileEntity;
import sg.ctx.file.mgr.service.FileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;

@Controller
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/file/upload")
    public void upload(@RequestParam("file") MultipartFile uploadFile, HttpServletResponse response) throws IOException, NoSuchAlgorithmException {
        var file = this.fileService.saveFile(1, uploadFile.getOriginalFilename(), uploadFile.getInputStream(), uploadFile.getSize());
        String fileId = String.valueOf(file.getId());
        response.addHeader("fileId", fileId);
    }

    @GetMapping("/file/download")
    public void download(int fileId, HttpServletRequest request,
                         HttpServletResponse response) throws IOException {

        FileEntity fileEntity = this.fileService.getFile(fileId);
        if(fileEntity!=null)
        {
            // 配置文件下载
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            // 下载文件能正常显示中文
            String fileName = fileEntity.getLogicName() + "." + fileEntity.getExt();
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            OutputStream os = response.getOutputStream();
            os.write(fileEntity.getContent());
        }
    }
}
