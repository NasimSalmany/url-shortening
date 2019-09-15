package com.example.urlShortening.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Nasim Salmany
 */

@Entity
@Table(name = "TBL_SHORTEN_URL"
        , indexes = @Index(name = "index_original_url", columnList = "original_url", unique = true)
        , uniqueConstraints = @UniqueConstraint(columnNames = "shorten_key", name = "shorten_url_uq_shorten_key"))
public class ShortenUrl {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "ORIGINAL_URL")
    private String originalUrl;

    @Column(name = "SHORTEN_KEY")
    private String shortenKey;

    @CreationTimestamp
    @Column(name = "create_time_stamp", updatable = false)
    private LocalDateTime createTimeStamp;

    public ShortenUrl() {
    }

    public ShortenUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public ShortenUrl(String originalUrl, String shortenUrl) {
        this.originalUrl = originalUrl;
        this.shortenKey = shortenUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortenKey() {
        return shortenKey;
    }

    public void setShortenKey(String shortenKey) {
        this.shortenKey = shortenKey;
    }

    public LocalDateTime getCreateTimeStamp() {
        return createTimeStamp;
    }

    public void setCreateTimeStamp(LocalDateTime createTimeStamp) {
        this.createTimeStamp = createTimeStamp;
    }
}
