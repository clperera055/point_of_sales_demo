package com.example.demo.controller;

import com.example.demo.dto.ItemDTO;
import com.example.demo.dto.paginate.ResponseActiveItemPage;
import com.example.demo.dto.paginate.ResponseItemDTOPage;
import com.example.demo.dto.request.ItemSaveDTO;
import com.example.demo.dto.request.ItemUpdateDTO;
import com.example.demo.dto.response.ResponseActiveItemsDTO;
import com.example.demo.service.ItemService;
import com.example.demo.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/item")

public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/save-item")
    public ResponseEntity<StandardResponse> addItem(@RequestBody ItemSaveDTO saveDTO) {
        String save = itemService.addItem(saveDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, saveDTO.getItemName() + " saved successfully...", save),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/get-all-items")
    public ResponseEntity<StandardResponse> getAllItems() {
        List<ItemDTO> getItems = itemService.getAllItems();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "All items are here...", getItems),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update-item/{id}")
    public ResponseEntity<StandardResponse> updateItem(@RequestBody ItemUpdateDTO updateDTO,
                                                       @PathVariable(value = "id") int id) {
        String updateItem = itemService.updateItem(updateDTO, id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(202, "Successfully Updated...", updateItem),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping("/get-all-items-by-state/{state}")
    public ResponseEntity<StandardResponse> getAllItemsByState(@PathVariable(value = "state") String state) {
        if (state.equalsIgnoreCase("active") || state.equalsIgnoreCase("inactive")) {
            boolean val = state.equalsIgnoreCase("active") ? true : false;
            List<ItemDTO> itemDTOS = itemService.getAllItemsByState(val);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(202, " Successfully Updated...", itemDTOS),
                    HttpStatus.ACCEPTED
            );
        }else if(state.equalsIgnoreCase("all")){
            List<ItemDTO> itemDTOS = itemService.getAllItems();
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(202, " Successfully Updated...", itemDTOS),
                    HttpStatus.ACCEPTED
            );
        }else {
            String error = "Error";
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(202, "Invalid State Entry...", error),
                    HttpStatus.ACCEPTED
            );
        }
    }

    @GetMapping("/get-all-active-items-details")
    public ResponseEntity<StandardResponse> getAllActiveItems() {
        List<ResponseActiveItemsDTO> activeItemsDTOS = itemService.getAllActiveItems();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "All items are here...", activeItemsDTOS),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/get-all-item-count/{state}")
    public ResponseEntity<StandardResponse> countAllItemsByState(@PathVariable(value = "state") String state) {
        if (state.equalsIgnoreCase("active") || state.equalsIgnoreCase("inactive")) {
            boolean val = state.equalsIgnoreCase("active") ? true : false;
            int count = itemService.countAllByActiveStatus(val);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(202, "All " + state + " count...", count),
                    HttpStatus.ACCEPTED
            );
        }else if(state.equalsIgnoreCase("all")){
            int count = itemService.countAll();
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(202, "All items count...", count),
                    HttpStatus.ACCEPTED
            );
        }else {
            String error = "Error";
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(202, "Invalid State Entry...", error),
                    HttpStatus.ACCEPTED
            );
        }
    }

    @GetMapping("/get-all-items-by-page/{page}")
    public ResponseEntity<StandardResponse> getAllItemsByPage(@PathVariable (value = "page") int page) {
        int size = 0;
        ResponseItemDTOPage itemPage = itemService.getAllItemsByPage(page, size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "All items in page : " + page, itemPage),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/get-all-active-items-page/{page}")
    public ResponseEntity<StandardResponse> getAllActiveItemsByPage(@PathVariable (value = "page") int page) {
        int size = 0;
        ResponseActiveItemPage itemPage = itemService.getAllActiveItemsByPage(page, size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "All active items in page : " + page, itemPage),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/get-all-items-page-by-state/{page},{state}")
    public ResponseEntity<StandardResponse> getAllItemsPageByState(@PathVariable(value = "page") int page,
                                                                   @PathVariable (value = "state") String state) {
        int size = 0;
        if(state.equalsIgnoreCase("active") || state.equalsIgnoreCase("inactive")){
            boolean val = state.equalsIgnoreCase("active") ? true : false;
            ResponseActiveItemPage itemPage = itemService.getAllItemsPageByState(val ,page, size);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(201, "All " + state + " items in page : " + page, itemPage),
                    HttpStatus.CREATED
            );
        }else if(state.equalsIgnoreCase("all")){
            ResponseActiveItemPage itemPage = itemService.getAllItemPages(page,size);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(201, "All items in page : " + page, itemPage),
                    HttpStatus.CREATED
            );
        }else{
            String ex = "error";
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse(201, "Invalid state entry.....", ex),
                    HttpStatus.CREATED
            );
        }
    }
}
