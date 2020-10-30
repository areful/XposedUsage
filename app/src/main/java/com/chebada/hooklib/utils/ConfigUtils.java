package com.chebada.hooklib.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

/**
 * Created by gj21798 on 2020/09/24.
 */
public class ConfigUtils {
    private static final String FILE_NAME = Environment.getExternalStorageDirectory() + File.separator + "xposed_package_name";

    public static boolean setPackageName(String text) {
        Writer writer = null;
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                boolean isCreateSuccess = file.createNewFile();
                if (!isCreateSuccess) {
                    return false;
                }
            }

            writer = new FileWriter(file, false); // true表示追加
            writer.write(text);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public static String getPackageName() {
        InputStream is = null;
        try {
            is = new FileInputStream(FILE_NAME);
            int iAvail = is.available();
            byte[] bytes = new byte[iAvail];
            is.read(bytes);
            return new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "";
    }
}
