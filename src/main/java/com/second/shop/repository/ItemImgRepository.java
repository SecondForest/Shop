package com.second.shop.repository;

import com.second.shop.entity.ItemImg;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {

  List<ItemImg> findByIdOrderByIdAsc(Long itemId);

}
