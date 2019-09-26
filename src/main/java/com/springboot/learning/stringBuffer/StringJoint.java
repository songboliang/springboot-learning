package com.springboot.learning.stringBuffer;

public class StringJoint {

    public static void main(String args[]){

        String s ="export JAVA_HOME=/home/boco4a/jdk1.8.0_101\n" +
                "export MAVEN_HOME=\"/home/boco4a/apache-maven-3.6.0\"\n" +
                "export PATH=$MAVEN_HOME/bin:$PATH\n" +
                "mvn clean \n" +
                "\n" +
                "mvn compile \n" +
                "mvn package -DskipTests -T1C -pl common -pl permission-data -pl permission-controller\n" +
                "\n" +
                "export JAVA_HOME=~/jdk1.8.0_91\n" +
                "export MAVEN_HOME=/home/boco4a/apache-maven-3.6.0 \n" +
                "export PATH=$MAVEN_HOME/bin:$PATH\n" +
                "cp /home/boco4a/jenkins/workspace/GerritConf/init/formatSonarConf.sh ./formatSonarConf.sh\n" +
                "chmod 755 ./*.sh\n" +
                "./formatSonarConf.sh $1 'maven' $2 '**/src/main/*.java'\n" +
                "/home/boco4a/sonar-runner7/bin/sonar-runner";

        StringBuffer stringBuffer = new StringBuffer();
        String[] split = s.split("\\$1");
        String[] split1 = split[1].split("\\$2");
        stringBuffer.append(split[0]);
        stringBuffer.append("branchname ");
        stringBuffer.append(split1[0]);
        stringBuffer.append("projectname");
        stringBuffer.append(split1[1]);
        System.out.println(stringBuffer.toString());

    }

}
