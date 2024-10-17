package com.example.demo.dto.response;

import com.example.demo.dto.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseItemCountDTO {

    private List<ItemDTO> list;
    private long count;
}
