FROM sippe/sippe-microservice-image-base:rhel
ADD maven/acquirer-ms-payment-gateway-link.jar /opt/microservices/acquirer-ms-payment-gateway-link.jar
ENTRYPOINT ["java", "-Duser.timezone=America/Sao_Paulo", "-Duser.country=br", "-Duser.language=pt" ,"-Djava.security.egd=file:/dev/./urandom", "-Xmx1024m", "-jar", "acquirer-ms-payment-gateway-link.jar"]