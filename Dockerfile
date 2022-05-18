FROM gradle:7-jdk17-alpine
COPY . /household
WORKDIR /household
RUN gradle build -x test

CMD gradle bootRun
