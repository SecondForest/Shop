package com.second.shop.service;

import com.second.shop.dto.ItemFormDto;
import com.second.shop.entity.Item;
import com.second.shop.entity.ItemImg;
import com.second.shop.repository.ItemImgRepository;
import com.second.shop.repository.ItemRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;

  private final ItemImgService itemImgService;

  private final ItemImgRepository itemImgRepository;

  public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList)
      throws Exception {

    //상품 등록
    Item item = itemFormDto.createItem();
    itemRepository.save(item);

    //이미지 등록
    for (int i = 0; i < itemImgFileList.size(); i++) {
      ItemImg itemImg = new ItemImg();
      itemImg.setItem(item);

      if (i == 0) {
        itemImg.setRepimgYn("Y");
      } else {
        itemImg.setRepimgYn("N");
      }

      itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
    }

    return item.getId();
  }
}
