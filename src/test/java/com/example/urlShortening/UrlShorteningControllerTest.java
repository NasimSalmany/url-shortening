package com.example.urlShortening;

import com.example.urlShortening.dao.ShortenUrlRepository;
import com.example.urlShortening.dto.OriginalUrlDto;
import com.example.urlShortening.services.ShortenUrlService;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @author Nasim Salmany
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UrlShorteningControllerTest {

    @Autowired
    private ShortenUrlService urlService;

    @Autowired
    private ShortenUrlRepository shortenUrlRepository;

    @Test
    public void success_shorten_valid_long_url() {
        String originalURL = "https://www.google.com/?client=safari&channel=iphone_bm";
        OriginalUrlDto originalURLDto = new OriginalUrlDto();
        originalURLDto.setOriginalUrl(originalURL);

        assertThat((shortenUrlRepository.count()), equalTo(0L));

        given()
                .contentType("application/json")
                .body(originalURLDto)
                .when()
                .post("/api/v1/url/shorten")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("shortenUrl", notNullValue())
        ;

        assertThat(shortenUrlRepository.findByOriginalUrl(originalURL).isPresent(), equalTo(true));
    }

    @Test
    public void success_return_shorten_url_when_original_url_exist_in_db() {
        assertThat((shortenUrlRepository.count()), equalTo(0L));
        // Save originalURL in db
        String originalURL = "https://www.google.com/?client=safari&channel=iphone_bm";
        String shortenURL = urlService.shortenUrl(originalURL);

        assertThat((shortenUrlRepository.count()), equalTo(1L));

        OriginalUrlDto originalURLDto = new OriginalUrlDto();
        originalURLDto.setOriginalUrl(originalURL);

        given()
                .contentType("application/json")
                .body(originalURLDto)
                .when()
                .post("/api/v1/url/shorten")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("shortenUrl", equalTo(shortenURL))
        ;
        assertThat((shortenUrlRepository.count()), equalTo(1L));
        assertThat(shortenUrlRepository.findByOriginalUrl(originalURL).isPresent(), equalTo(true));

    }


    @Test
    public void fail_shorten_invalid_pattern_long_url() {

        OriginalUrlDto originalURLDto = new OriginalUrlDto();
        originalURLDto.setOriginalUrl("lp/whitepaper/api/digital-platform?utm_source=twitter&utm_medium=cpc");

        given()
                .contentType("application/json")
                .body(originalURLDto)
                .when()
                .post("/api/v1/url/shorten")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .contentType(ContentType.JSON)
                .body("errorMessage", notNullValue())
        ;
    }

    @Test
    public void fail_shorten_null_long_url() {

        OriginalUrlDto originalURLDto = new OriginalUrlDto();
        originalURLDto.setOriginalUrl(null);

        given()
                .contentType("application/json")
                .body(originalURLDto)
                .when()
                .post("/api/v1/url/shorten")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .contentType(ContentType.JSON)
                .body("errorMessage", notNullValue())
        ;

    }


    @Test
    public void fail_shorten_empty_long_url() {

        OriginalUrlDto originalURLDto = new OriginalUrlDto();
        originalURLDto.setOriginalUrl("");

        given()
                .contentType("application/json")
                .body(originalURLDto)
                .when()
                .post("/api/v1/url/shorten")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .contentType(ContentType.JSON)
                .body("errorMessage", notNullValue())

        ;
    }


    @Test
    public void fail_shorten_blank_long_url() {

        OriginalUrlDto originalURLDto = new OriginalUrlDto();
        originalURLDto.setOriginalUrl(" ");

        given()
                .contentType("application/json")
                .body(originalURLDto)
                .when()
                .post("/api/v1/url/shorten")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .contentType(ContentType.JSON)
                .body("errorMessage", notNullValue())
        ;
    }

}