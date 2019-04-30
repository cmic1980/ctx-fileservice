package sg.ctx.file.mgr.model.json.request;

import java.util.List;

public class GetFileListRequest {
    private List<Integer> idList;

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }
}
