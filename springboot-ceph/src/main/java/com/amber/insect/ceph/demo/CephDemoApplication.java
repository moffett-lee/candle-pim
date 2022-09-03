package com.amber.insect.ceph.demo;

/**
 * @ClassName CephDemoApplication
 * @Description TODO
 * @Author Amber.L
 * @Date 2021/12/10 23:12
 * @Version 1.0
 **/
public class CephDemoApplication {
    public static void main(String[] args) {

        System.out.println("start....");
        String username = "admin";
        String monIp = "10.10.20.11:6789;10.10.20.12:6789;10.10.20.13:6789";
        String userKey = "AQBZBypdMchvBRAAbWVnIGyYNvxWQZ2UkuiYew==";
        String mountPath = "/";
        CephOperator cephOperate = null;
        try {
            String opt = (args == null || args.length < 1)? "" : args[0];
            cephOperate = new CephOperator(username, monIp, userKey, mountPath);
            if("upload".equals(opt)) {
                cephOperate.uploadFileByPath("/temp_upload_fs", args[1]);
            }else if("download".equals(opt)) {
                cephOperate.downloadFileByPath("/temp_download_fs", args[1]);
            }else {
                System.out.println("Unrecognized Command! Usage  opt[upload|download] filename[path]!");
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            if(null != cephOperate) {
                cephOperate.umount();
            }
        }
        System.out.println("end....");

    }
}
