package com.noryangjin.boostcourse.repository.file;

import com.noryangjin.boostcourse.domain.FileInfo;
import com.noryangjin.boostcourse.domain.QFileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class FileInfoSupportRepositoryImpl  extends QuerydslRepositorySupport implements FileInfoSupportRepository{

    private static final Logger logger = LoggerFactory.getLogger(FileInfoSupportRepositoryImpl.class);

    public FileInfoSupportRepositoryImpl(){super(FileInfo.class);}

    @Override
    public FileInfo findByFileName(long displayInfo_id, String fileName) {
        QFileInfo fileInfo = QFileInfo.fileInfo;

        String fileNames = String.valueOf(displayInfo_id) + "_" + fileName;

        logger.info(fileNames);

        return from(fileInfo)
                .where(
                        fileInfo.file_name.contains(fileNames)
                ).fetchFirst();
    }
}
