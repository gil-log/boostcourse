package com.noryangjin.boostcourse.repository.display;

import com.noryangjin.boostcourse.domain.DisplayInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisplayInfoRepository extends JpaRepository<DisplayInfo, Long>, DisplayInfoSupportRepository {
}
