package com.example.demo.service.impl;

import com.example.demo.dto.ItemDTO;
import com.example.demo.dto.paginate.ResponseActiveItemPage;
import com.example.demo.dto.paginate.ResponseItemDTOPage;
import com.example.demo.dto.request.ItemSaveDTO;
import com.example.demo.dto.request.ItemUpdateDTO;
import com.example.demo.dto.response.ResponseActiveItemsDTO;
import com.example.demo.entity.Item;
import com.example.demo.exception.EntryDuplicationException;
import com.example.demo.exception.EntryNotFoundException;
import com.example.demo.repo.ItemRepo;
import com.example.demo.service.ItemService;
import com.example.demo.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String addItem(ItemSaveDTO saveDTO) {
        Item item = itemMapper.saveItemToEntity(saveDTO);
        item.setActiveState(true);
        if (!itemRepo.existsById(item.getItemId())) {
            return itemRepo.save(item).getItemName();
        } else
            throw new EntryDuplicationException("Item Id is already exist...");
    }

    @Override
    public List<ItemDTO> getAllItems() {
        List<Item> items = itemRepo.findAll();
        if (items.size() > 0) {
            List<ItemDTO> itemDTOS = modelMapper.
                    map(items, new TypeToken<List<Item>>() {
                    }.getType());
            return itemDTOS;
        } else
            throw new EntryNotFoundException("No Items in the table");
    }

    @Override
    public String updateItem(ItemUpdateDTO updateDTO, int id) {
        if (itemRepo.existsById(id)) {
            itemRepo.updateItem(
                    updateDTO.getBalanceQty(), updateDTO.getSupplierPrice(),
                    updateDTO.getSellingPrice(), updateDTO.isActiveState(), id
            );
            return "item updated";
        } else
            throw new EntryNotFoundException("Invalid Customer id...");
    }

    @Override
    public List<ItemDTO> getAllItemsByState(boolean val) {
        List<Item> items = itemRepo.findAllByActiveStateEquals(val);
        List<ItemDTO> dtoList = itemMapper.findAllByActiveStateEquals(items);
        return dtoList;
    }

    @Override
    public List<ResponseActiveItemsDTO> getAllActiveItems() {
        List<Item> items = itemRepo.findAllByActiveStateEquals(true);
        if (items.size() > 0) {
            List<ResponseActiveItemsDTO> activeItemsDTOS = itemMapper.getAllActiveItems(items);
            return activeItemsDTOS;
        } else
            throw new EntryNotFoundException("Invalid Entry....");
    }

    @Override
    public int countAllByActiveStatus(boolean val) {
        int count = itemRepo.countAllByActiveStateEquals(val);
        return count;
    }

    @Override
    public int countAll() {
        int count = (int) itemRepo.count();
        return count;
    }

    @Override
    public ResponseItemDTOPage getAllItemsByPage(int page, int size) {
        Page<Item> itemPage = itemRepo.findAll(PageRequest.of(page, 5));
        return new ResponseItemDTOPage(
                itemMapper.entityListToPageList(itemPage),
                itemRepo.count()
        );
    }

    @Override
    public ResponseActiveItemPage getAllActiveItemsByPage(int page, int size) {
        List<Item> items = itemRepo.findAllByActiveStateEquals(true);
        if (items.size() > 0) {
            Page<Item> getAllItemPagination = itemRepo.findAllByActiveStateEquals(true, PageRequest.of(page, 5));
            return new ResponseActiveItemPage(
                    itemMapper.getAllActiveItemPage(getAllItemPagination),
                    itemRepo.countAllByActiveStateEquals(true)
            );
        }
        return null;
    }

    @Override
    public ResponseActiveItemPage getAllItemsPageByState(boolean val, int page, int size) {
        List<Item> itemList = itemRepo.findAllByActiveStateEquals(val);
        if(itemList.size() > 0){
            Page<Item> getAllItemsPageByState = itemRepo.findAllByActiveStateEquals(val,PageRequest.of(page, 5));

            return new ResponseActiveItemPage(
              itemMapper.getAllItemsPageByState(getAllItemsPageByState),
              itemRepo.countAllByActiveStateEquals(val)
            );
        }else
            throw  new EntryNotFoundException("Invalid Input....");

    }

    @Override
    public ResponseActiveItemPage getAllItemPages(int page, int size) {
        Page<Item> itemPage = itemRepo.findAll(PageRequest.of(page, 5));
        return new ResponseActiveItemPage(
                itemMapper.getAllItemPages(itemPage),
                itemRepo.count()
        );
    }


}
