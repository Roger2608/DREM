package com.drem.fileprocessing.util;

import com.drem.fileprocessing.util.constants.FileConstants;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;

import java.io.IOException;
import java.io.OutputStream;

public class CrudFile {

    public static void createNewFile(ResourceLoader resourceLoader, String containerName, String fileName, String text)
            throws IOException {
        Resource resource = resourceLoader.getResource(
                String.format(FileConstants.PATTERN_AZURE_BLOB, containerName, fileName)
        );
        try (OutputStream os = ((WritableResource) resource).getOutputStream()) {
            os.write(text.getBytes());
        }
    }

}
