package com.drem.almaceningfiles.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j
public class Util {
    public static String findFileName(Resource resource) throws IOException {
        String a = StreamUtils.copyToString(resource.getInputStream(), Charset.defaultCharset());
        int startIndex = a.indexOf("filename=");
        startIndex = startIndex + "filename=\"".length();
        int endIndex = a.indexOf("\"", startIndex);

        String fileName = a.substring(startIndex, endIndex);
        log.info("===> file name: {} <====", fileName);
        return fileName;
    }
    public static String findBodyFile(Resource resource) throws IOException{
        String a = StreamUtils.copyToString(resource.getInputStream(), Charset.defaultCharset());
        String[] lines = a.split("\n");
        StringBuilder result = new StringBuilder();
        for (int i = 3; i < lines.length - 2; i++) {
            result.append(lines[i]);
            result.append("\n");
        }
        return result.toString().trim();
    }
}
