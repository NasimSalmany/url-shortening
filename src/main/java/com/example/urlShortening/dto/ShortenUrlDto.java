package com.example.urlShortening.dto;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;

/**
 * @author Nasim Salmany
 */
public class ShortenUrlDto {

    @NotEmpty(message = "{val.err.shorten.url.not.empty.or.null}")
    @URL(message = "{val.err.shorten.url.invalid}")
    private String shortenUrl;

    public ShortenUrlDto() {
    }

    public ShortenUrlDto(String shortenUrl) {
        this.shortenUrl = shortenUrl;
    }

    public String getShortenUrl() {
        return shortenUrl;
    }

    public void setShortenUrl(String shortenUrl) {
        this.shortenUrl = shortenUrl;
    }
}
