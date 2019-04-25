package sg.ctx.file.mgr.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sg.ctx.file.mgr.service.FileService;

@RestController
public class DefaultController {
    @Autowired
    private FileService fileService;

    @GetMapping("/index")
    public ResponseEntity<?> index() {
        // this.fileService.s(1, "s.pdf", 1024 * 1024);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Hello World!");
        return ResponseEntity.ok().body(jsonObject);
    }
}
