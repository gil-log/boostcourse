package com.noryangjin.boostcourse.repository.displayInfo;

import com.noryangjin.boostcourse.domain.DisplayInfo;
import com.noryangjin.boostcourse.dto.DisplayInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface DisplayInfoRepository extends JpaRepository<DisplayInfo, Long>, DisplayInfoSupportRepository {
    //Page<DisplayInfoDTO.DisplayInfos> findAll(Pageable pageable);
}
