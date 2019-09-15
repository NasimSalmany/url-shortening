package com.example.urlShortening.services;

import com.example.urlShortening.entities.ShortenUrl;

import java.util.Optional;

/**
 * @author Nasim Salmany
 */
public interface ShortenUrlService {

    String shortenUrl(String originalUrl);

    Optional<ShortenUrl> getShortenUrl(String shortenUrl);
}
