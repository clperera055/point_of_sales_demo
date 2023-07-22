package com.example.demo.util.mappers;

import com.example.demo.dto.ItemDTO;
import com.example.demo.dto.request.ItemSaveDTO;
import com.example.demo.dto.response.ResponseActiveItemsDTO;
import com.example.demo.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item saveItemToEntity(ItemSaveDTO saveDTO);

    List<ItemDTO> findAllByActiveStateEquals(List<Item> items);

    List<ResponseActiveItemsDTO> getAllActiveItems(List<Item> items);

    List<ItemDTO> entityListToPageList(Page<Item> itemPage);

    List<ResponseActiveItemsDTO> getAllActiveItemPage(Page<Item> getAllItemPagination);

    List<ResponseActiveItemsDTO> getAllItemsPageByState(Page<Item> getAllItemsPageByState);

    List<ResponseActiveItemsDTO> getAllItemPages(Page<Item> itemPage);
}
