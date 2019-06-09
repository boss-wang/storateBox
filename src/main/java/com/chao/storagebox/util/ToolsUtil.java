package com.chao.storagebox.util;

import java.util.UUID;

public class ToolsUtil
{
    public static String generateUUID()
    {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-", "");
    }
}
