package com.example.EStockMarketApplication.Exceptions;

@SuppressWarnings("serial")
public class CompanyNotFound extends RuntimeException{
    public CompanyNotFound(String Message)
    {
        super(Message);
    }
}
