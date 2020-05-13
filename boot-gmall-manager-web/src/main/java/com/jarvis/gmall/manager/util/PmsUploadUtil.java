package com.jarvis.gmall.manager.util;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * boot-gmall : com.jarvis.gmall.manager.util
 *
 * @author jarvis
 * @create 2020-03-28 22:09
 */
public class PmsUploadUtil {

    public static String uploadImg(MultipartFile multipartFile) {
        String imgUrl = "http://192.168.101.20";
        try {
            String file = PmsUploadUtil.class.getClass().getResource("/tracker.conf").getFile();
            ClientGlobal.init(file);
            TrackerClient trackerClient = new TrackerClient();

            TrackerServer trackerServer = trackerClient.getTrackerServer();

            StorageServer storageServer = null;
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);

            String originalFilename = multipartFile.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

            String[] upload_file = client.upload_file(multipartFile.getBytes(), extName, null);
            for (int i = 0; i < upload_file.length; i++) {
                imgUrl = imgUrl + "/" + upload_file[i];
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return imgUrl;
    }
}
