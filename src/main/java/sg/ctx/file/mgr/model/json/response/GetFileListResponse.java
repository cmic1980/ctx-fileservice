package sg.ctx.file.mgr.model.json.response;

import sg.ctx.file.mgr.domain.FileEntity;

import java.util.List;

public class GetFileListResponse {
    private List<FileEntity> fileEntityList;

    public List<FileEntity> getFileEntityList() {
        return fileEntityList;
    }

    public void setFileEntityList(List<FileEntity> fileEntityList) {
        this.fileEntityList = fileEntityList;
    }
}
