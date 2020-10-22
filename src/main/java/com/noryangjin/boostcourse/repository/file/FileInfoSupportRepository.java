package com.noryangjin.boostcourse.repository.file;

import com.noryangjin.boostcourse.domain.FileInfo;

public interface FileInfoSupportRepository {

    public FileInfo findByFileName(long displayInfo_id, String fileName);
}
