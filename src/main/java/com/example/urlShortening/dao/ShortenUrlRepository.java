package com.example.urlShortening.dao;

import com.example.urlShortening.entities.ShortenUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Nasim Salmany
 */

@Repository
public interface ShortenUrlRepository extends JpaRepository<ShortenUrl, Long> {

    Optional<ShortenUrl> findByOriginalUrl(String originalUrl);

}
