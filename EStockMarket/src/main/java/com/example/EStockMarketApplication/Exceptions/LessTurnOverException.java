package com.example.EStockMarketApplication.Exceptions;

@SuppressWarnings("serial")
public class LessTurnOverException extends RuntimeException {
    public LessTurnOverException(String Message)
    {
        super(Message);
    }
}
