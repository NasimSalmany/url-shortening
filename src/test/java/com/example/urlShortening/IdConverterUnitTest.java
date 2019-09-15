package com.example.urlShortening;

import com.example.urlShortening.services.IdConverterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author Nasim Salmany
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class IdConverterUnitTest {


    @Autowired
    private IdConverterService idConverterService;


    @Test
    public void success_encode_125_base10_to_base62() {
        String base62Str = idConverterService.encode(125);
        assertEquals("cb", base62Str);
    }


    @Test
    public void success_encode_19158_base10_to_base62() {
        String s = idConverterService.encode(19158);
        assertEquals("e9a", s);
    }

    @Test
    public void success_encode_base10_to_base62() {
        assertEquals("ba", idConverterService.encode(62));
        assertEquals("bb", idConverterService.encode(63));
        assertEquals("bc", idConverterService.encode(64));
        assertEquals("b9", idConverterService.encode(123));
        assertEquals("ca", idConverterService.encode(124));
        assertEquals("c9", idConverterService.encode(185));
    }

    @Test
    public void success_encode_biggest_possible_integer_base10_to_base62() {
        assertEquals("cvuMLb", idConverterService.encode(Integer.MAX_VALUE));
    }

    @Test
    public void success_decode_base62_to_base10() {
        long i = idConverterService.decode("cb");
        assertEquals(125, i);
        long j = idConverterService.decode("e9a");
        assertEquals(19158, j);
    }

}
