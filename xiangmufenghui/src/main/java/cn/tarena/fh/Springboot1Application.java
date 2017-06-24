package cn.tarena.fh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@ServletComponentScan
@SpringBootApplication
@MapperScan("cn.tarena.fh.mapper") //扫描Mapper包 为其创建代理对象
public class Springboot1Application extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(Springboot1Application.class, args);
	}
	
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	        return builder.sources(Springboot1Application.class);
	    }
}
