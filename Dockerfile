FROM tomcat:10.1-jdk21-temurin

RUN rm -rf /usr/local/tomcat/webapps/*

COPY com /tmp/com
COPY webapp /tmp/webapp

RUN mkdir -p /tmp/classes && \
    javac -cp "/usr/local/tomcat/lib/*" \
    -d /tmp/classes \
    $(find /tmp/com -name "*.java") && \
    mkdir -p /tmp/StudySync/WEB-INF/classes && \
    cp -r /tmp/classes/* /tmp/StudySync/WEB-INF/classes/ && \
    cp -r /tmp/webapp/* /tmp/StudySync/ && \
    cd /tmp/StudySync && \
    jar -cf /usr/local/tomcat/webapps/ROOT.war .

EXPOSE 8080

CMD ["catalina.sh", "run"]
