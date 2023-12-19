	package com.example.EStockMarketApplication.Controllers;

import com.example.EStockMarketApplication.Models.Company;
import com.example.EStockMarketApplication.Models.StockPrice;
import com.example.EStockMarketApplication.Service.StockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/v1.0/market/stock")
public class StockPriceController {
    private StockPriceService stockPriceService;

    @Autowired
    public StockPriceController(StockPriceService stockPriceService) {
        this.stockPriceService = stockPriceService;
    }

    @PostMapping("/add/{companyCode}")
    public ResponseEntity<String> addNewPrice(@PathVariable long companyCode, @RequestBody StockPrice price)
    {
         stockPriceService.UpdateStockPrice(companyCode,price);
         return ResponseEntity.ok("New Price Updated for the Company");
    }
}
