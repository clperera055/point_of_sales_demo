package com.example.demo.dto.paginate;

import com.example.demo.dto.response.ResponseOrderDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PaginatedResponseOrderDetails {

    private List<ResponseOrderDetailsDTO> list;
    private long dataCount;


}
