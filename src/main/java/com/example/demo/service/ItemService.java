package com.example.demo.service;

import com.example.demo.dto.ItemDTO;
import com.example.demo.dto.paginate.ResponseActiveItemPage;
import com.example.demo.dto.paginate.ResponseItemDTOPage;
import com.example.demo.dto.request.ItemSaveDTO;
import com.example.demo.dto.request.ItemUpdateDTO;
import com.example.demo.dto.response.ResponseActiveItemsDTO;

import java.util.List;

public interface ItemService {
    String addItem(ItemSaveDTO saveDTO);

    List<ItemDTO> getAllItems();

    String updateItem(ItemUpdateDTO updateDTO, int id);


    List<ItemDTO> getAllItemsByState( boolean val);

    List<ResponseActiveItemsDTO> getAllActiveItems();

    int countAllByActiveStatus(boolean val);

    int countAll();

    ResponseItemDTOPage getAllItemsByPage(int page, int size);

    ResponseActiveItemPage getAllActiveItemsByPage(int page, int size);

    ResponseActiveItemPage getAllItemsPageByState(boolean val, int page, int size);

    ResponseActiveItemPage getAllItemPages(int page, int size);
}
