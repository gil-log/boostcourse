package com.noryangjin.boostcourse.repository.file;

import com.noryangjin.boostcourse.domain.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo, Long>, FileInfoSupportRepository {
    public FileInfo findById(long id);
}

interface FileInfoSupportRepository {

    public FileInfo findByFileName(long displayInfo_id, String fileName);
}
