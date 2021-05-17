FROM openjdk:11
VOLUME /home/projects/finance_bckp
ADD build/libs/finance.jar finance.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/finance.jar"]
