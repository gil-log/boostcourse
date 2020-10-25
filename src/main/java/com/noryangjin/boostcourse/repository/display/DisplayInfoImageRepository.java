package com.noryangjin.boostcourse.repository.display;

import com.noryangjin.boostcourse.domain.DisplayInfoImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisplayInfoImageRepository extends JpaRepository<DisplayInfoImage, Long>, DisplayInfoImageSupportRepositorya {

}
interface DisplayInfoImageSupportRepositorya {

    // displayInfoId로 displayInfoImage 출력
    public DisplayInfoImage getDisplayInfoImageByDisplayInfoId(long displayInfoId);
}