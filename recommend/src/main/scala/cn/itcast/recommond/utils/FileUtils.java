package cn.itcast.recommond.utils;

import java.io.File;

/**
 * Created by lgh on 2018/3/27.
 */
public class FileUtils {
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    public static void mkdirFile(File file) {
        File dir = file.getParentFile();
        //如果路径不存在，新建
        if(!file.exists()&&!file.isDirectory()) {
            file.mkdirs();
        }
    }
}
