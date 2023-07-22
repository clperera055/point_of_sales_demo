package com.example.demo.service.impl;

import com.example.demo.dto.request.OrderRequestSaveDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetails;
import com.example.demo.exception.EntryNotFoundException;
import com.example.demo.repo.CustomerRepo;
import com.example.demo.repo.ItemRepo;
import com.example.demo.repo.OrderDetailsRepo;
import com.example.demo.repo.OrderRepo;
import com.example.demo.service.OrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    @Override
    public String addOrder(OrderRequestSaveDTO orderSaveDTO) {

        Order order = new Order(
          customerRepo.getReferenceById(orderSaveDTO.getCustomerId()),
            orderSaveDTO.getDate(),
                orderSaveDTO.getTotal()
        );

        orderRepo.save(order); // here save the order

        //then save data to order details table
        if(orderRepo.existsById(order.getOrderId())){
            List<OrderDetails> orderDetails = modelMapper.map(orderSaveDTO.getOrderDetails(),
                    new TypeToken<List<OrderDetails>>(){}.getType());

            //get order id and relevant item id according to the item name
            for(int i = 0; i < orderDetails.size(); i++){
                orderDetails.get(i).setOrders(order);
                orderDetails.get(i).setItems(itemRepo.getReferenceById(orderSaveDTO.getOrderDetails().get(i).getItemId()));
            }

            //finally save order details
            if(orderDetails.size() > 0){
                orderDetailsRepo.saveAll(orderDetails);
            }

            return "saved";
        }
        throw new EntryNotFoundException("Order Save Unsuccessful....");
    }
}
