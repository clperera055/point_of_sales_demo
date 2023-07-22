package com.example.demo.dto.paginate;

import com.example.demo.dto.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ResponseItemDTOPage {

    private List<ItemDTO> dtoList;
    private long data;

}
