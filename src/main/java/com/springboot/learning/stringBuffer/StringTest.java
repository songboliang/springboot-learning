package com.springboot.learning.stringBuffer;

public class StringTest {

    public static void main(String args[]){

        String buildLog= "10:35:51.302 INFO  - ANALYSIS SUCCESSFUL, you can browse http://188.103.142.52:9000/dashboard?id=ts-service\n" +
                "\n" +
                "10:35:51.303 INFO  - Note that you will be able to access the updated dashboard once the server has processed the submitted analysis report\n" +
                "\n" +
                "10:35:51.303 INFO  - More about the report processing at http://188.103.142.52:9000/api/ce/task?id=AW0eLVg6Q75l4qY6BFr4";

//        if (org.apache.commons.lang.StringUtils.isNotEmpty(buildLog) && buildLog.contains("ts-service")) {
//            int startIndex = buildLog.lastIndexOf("dashboard/index") + "dashboard/index/".length();
//            int endIndex = buildLog.lastIndexOf("/api/ce/");
//            if (org.apache.commons.lang.StringUtils.isNotEmpty(buildLog.substring(startIndex, endIndex))) {
//                String sonarName = buildLog.substring(startIndex, endIndex).split("\n")[0];
//                System.out.println(sonarName);
//                String dealScan = "<a target=\"_blank\" href=http://188.103.142.52:9000/dashboard? " + "id=" + sonarName + " >" + "查看详情" + "</a>";
//            }
        String url = buildLog.split("you can browse ")[1].split("\n")[0];
        String dealScan = "<a target=\"_blank\" href=" +url+ " >" + "查看详情" + "</a>";
        System.out.println(url);



    }

}
