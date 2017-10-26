package com.kyq.zwj.backend.helper;

import com.kyq.zwj.backend.attributes.CommonAttribute;
import com.kyq.zwj.backend.util.MapUtil;
import com.kyq.zwj.backend.util.StringUtil;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:  DFSFileHelper
 * Copyright: © 2015 CSNT. All rights reserved.
 * Company:CSNT中海网络科技股份有限公司
 *
 * @author kyq1024
 * @version 1.0
 */

public class DFSFileHelper {
    private static final Logger tracer = LoggerFactory.getLogger(DFSFileHelper.class);
    private static TrackerClient trackerClient = null;

    static {
        String fdfsClientConfigPath = Thread.currentThread().getContextClassLoader().getResource("fdfs_client.conf").getPath();
        try {
            ClientGlobal.init(fdfsClientConfigPath);
            trackerClient = new TrackerClient();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String getToken(String fileName, int ts, String secretKey) {
        String token = null;
        try {
            token = ProtoCommon.getToken(fileName, ts, secretKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    public static TrackerServer getConnection() {
        try {
            return trackerClient.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Map upload(byte[] fileContent, String extName, Map<String, String> metaMap) {
        Map retMap = new HashMap();
        TrackerServer trackerServer = getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        try {
            NameValuePair[] meta_list = null;
            if (MapUtil.isNotEmpty(metaMap)) {
                meta_list = new NameValuePair[metaMap.size()];
                int i = 0;
                for (String key : metaMap.keySet()) {
                    meta_list[i++] = new NameValuePair(key, StringUtil.replaceNull(metaMap.get(key)));
                }
            }
            String fileIds[] = storageClient.upload_file(fileContent, extName, meta_list);
            retMap.put(CommonAttribute.GROUP_NAME, fileIds[0]);
            retMap.put(CommonAttribute.FILE_PATH, fileIds[1]);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                trackerServer.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return retMap;
    }

    public static byte[] download(String groupName, String filePath) {
        byte[] retBytes = null;
        TrackerServer trackerServer = getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        try {
            retBytes = storageClient.download_file(groupName, filePath);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                trackerServer.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return retBytes;
    }

    public static void deleteFile(String groupName, String filePath) {
        TrackerServer trackerServer = getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        try {
            storageClient.delete_file(groupName, filePath);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                trackerServer.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    public static String getDownloadUrl(String dfsFilePath, String dfsGroupName, String fileName){
        int ts = (int)(System.currentTimeMillis() / 1000L);
        String token = null;

        try {
            token = DFSFileHelper.getToken(dfsFilePath, ts, SysconfigHelper.getProperty("file_manager_download_secret_key"));
        } catch (Exception ex) {
            tracer.warn("Error calculating file download token.", ex);
        }

        String fileFullPath = SysconfigHelper.getProperty("file_manager_download_url") + dfsGroupName + "/" + dfsFilePath + "?token=" + token + "&ts=" + ts + "&filename=" + fileName;
        return fileFullPath;
    }
}
