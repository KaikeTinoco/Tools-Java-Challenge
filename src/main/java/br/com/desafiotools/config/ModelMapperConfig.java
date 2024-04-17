package br.com.desafiotools.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
//essa classe tem que ser adicionada nas dependencias:
    /*<dependency>
			<groupId>org.modelmapper</groupId>
			<artifactId>modelmapper</artifactId>
			<version>3.2.0</version>
		</dependency>*/
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
