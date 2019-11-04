FROM openjdk:8u212

ENV SPRING_BOOT_EXAMPLE_HOME=/usr/local/spring-boot-example
ENV VERSION=1.0-SNAPSHOT

COPY build/spring-boot-example-${VERSION}/ ${SPRING_BOOT_EXAMPLE_HOME}/

RUN chmod 755 ${SPRING_BOOT_EXAMPLE_HOME}/bin/*.sh
RUN chmod 755 ${SPRING_BOOT_EXAMPLE_HOME}/flyway/flyway

WORKDIR ${SPRING_BOOT_EXAMPLE_HOME}

EXPOSE 8080/tcp

CMD ["/usr/local/spring-boot-example/bin/spring-boot-example-start.sh"]