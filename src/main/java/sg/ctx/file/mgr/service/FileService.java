package sg.ctx.file.mgr.service;

import sg.ctx.file.mgr.domain.FileEntity;

import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    FileEntity saveFile(int bucketId, String fileName, InputStream inputStream, long fileSize) throws IOException;
    FileEntity getFile(int fileId) throws IOException;
}
