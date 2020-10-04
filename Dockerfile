FROM tomcat:8.5.20-jre8-alpine
USER root
ADD target/user-info /usr/local/tomcat/webapps/
EXPOSE 8081
VOLUME /usr/local/tomcat/webapps/
CMD ["catalina.sh", "run"]