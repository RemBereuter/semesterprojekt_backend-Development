package com.example.semesterprojektbackend.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

class ImageDTOTest {
    @Test
    void testConstructor() throws UnsupportedEncodingException {
        ImageDTO actualImageDTO = new ImageDTO();
        MultipartFile[] multipartFileArray = new MultipartFile[]{
                new MockMultipartFile("Name", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8"))};
        actualImageDTO.setFile(multipartFileArray);
        MultipartFile[] file = actualImageDTO.getFile();
        assertEquals(1, file.length);
        assertNull(actualImageDTO.getItemNumber());
        assertSame(file, multipartFileArray);
    }
}

