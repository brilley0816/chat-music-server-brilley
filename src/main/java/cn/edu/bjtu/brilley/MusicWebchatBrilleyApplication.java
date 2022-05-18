package cn.edu.bjtu.brilley;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.edu.bjtu.brilley.dao")
public class MusicWebchatBrilleyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicWebchatBrilleyApplication.class, args);
    }

}
