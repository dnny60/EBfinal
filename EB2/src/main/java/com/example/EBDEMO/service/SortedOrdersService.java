package com.example.EBDEMO.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EBDEMO.Repository.OrderRepository;
import com.example.EBDEMO.model.Order;

@Service
public class SortedOrdersService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllSortedOrder() {
        return (List<Order>) orderRepository.findAllByOrderByODateAsc();
    }

    // 其他必要的业务逻辑
}