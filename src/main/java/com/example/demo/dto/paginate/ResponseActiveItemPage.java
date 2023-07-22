package com.example.demo.dto.paginate;

import com.example.demo.dto.response.ResponseActiveItemsDTO;
import com.example.demo.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ResponseActiveItemPage {

    private List<ResponseActiveItemsDTO> dtoList;
    private long data;

}
