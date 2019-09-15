package com.example.urlShortening.services;

/**
 * @author Nasim Salmany
 */
public interface IdConverterService {

    String encode(long num);

    long decode(String shortenKey);
}
