package sg.ctx.file.mgr.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sg.ctx.file.mgr.domain.FileEntity;
import sg.ctx.file.mgr.model.json.request.GetFileListRequest;
import sg.ctx.file.mgr.model.json.response.GetFileListResponse;
import sg.ctx.file.mgr.service.FileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/file/upload")
    @ResponseBody
    public JSONObject upload(@RequestParam("file") MultipartFile uploadFile, HttpServletResponse response) throws IOException, NoSuchAlgorithmException {
        var file = this.fileService.saveFile(1, uploadFile.getOriginalFilename(), uploadFile.getInputStream(), uploadFile.getSize());
        String fileId = String.valueOf(file.getId());
        response.addHeader("fileId", fileId);

        JSONObject result = new JSONObject();
        result.put("fileId", fileId);
        return result;
    }

    @GetMapping("/file/download")
    public void download(int fileId, HttpServletRequest request,
                         HttpServletResponse response) throws IOException {

        FileEntity fileEntity = this.fileService.getFile(fileId);
        if (fileEntity != null) {
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

    @RequestMapping(value = "/file/list", method = RequestMethod.POST)
    @ResponseBody
    public GetFileListResponse getFileList(@RequestBody GetFileListRequest request) {
        GetFileListResponse response = new GetFileListResponse();
        List<Integer> idList = request.getIdList();
        List<FileEntity> fileEntityList = this.fileService.getFileList(idList);
        response.setFileEntityList(fileEntityList);
        return response;
    }
}
