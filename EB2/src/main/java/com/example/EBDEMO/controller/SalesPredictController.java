package com.example.EBDEMO.controller;



import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.EBDEMO.Repository.OrderRepository;

import com.example.EBDEMO.model.MSalesPred;

import jakarta.persistence.EntityNotFoundException;
import com.example.EBDEMO.model.Order;
import com.example.EBDEMO.service.SalesDataResponse;

@Controller
@RequestMapping(path="/salesPredict")
public class SalesPredictController {

    @Autowired
    private OrderRepository orderRepository;
    
    
    @GetMapping("/get")
    public ResponseEntity<?> updateStage() {
    	
    	
    	Iterable<Order> ordersIterable = orderRepository.findAll();
    	SimpleDateFormat dtDateFormat = new SimpleDateFormat("MM");

    	// 使用Map来存储每个月的销售总量
    	Map<Double, Double> monthlySales = new LinkedHashMap<>();

    	for (Order order : ordersIterable) {
    		Double month = Double.valueOf(dtDateFormat.format(order.getODate()));
    	    Double quantity = Double.valueOf(order.getOQuantity());

    	    // 累加每个月的销售量
    	    monthlySales.merge(month, quantity, Double::sum);
    	}

    	// 分别创建月份和销售量的列表
    	ArrayList<Double> months = new ArrayList<>(monthlySales.keySet());
    	ArrayList<Double> sales = new ArrayList<>(monthlySales.values());

    	// 输出结果
    	System.out.println("Months: " + months);
    	System.out.println("Sales: " + sales);
    	
    	try {
            // 预测未来三个月
            MSalesPred mSalesPred = new MSalesPred(new ArrayList<>(months), new ArrayList<>(sales));
            List<Double> predictions = mSalesPred.getPred();

            // 获取最后一个月份
            double lastMonth = months.get(months.size() - 1);

            // 为预测的三个月份添加额外的月份
            for (int i = 1; i <= 3; i++) {
                lastMonth = lastMonth == 12 ? 1 : lastMonth + 1; // 如果到达12月，循环到1月
                months.add(lastMonth);
            }

            // 构建响应对象
            SalesDataResponse response = new SalesDataResponse();
            response.setMonths(months);
            response.setSales(sales);
            response.setPredictions(predictions);

            // 返回 JSON 响应
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during prediction");
        }
    }


    
    

}
