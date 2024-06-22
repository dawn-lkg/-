package com.dawn.dawn.common.system.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author chenliming
 * @date 2023/9/15 21:21
 */

public interface FileService {

    /**
     *
     * @param file
     * @return
     */
    String uploadFile(MultipartFile file);
}
