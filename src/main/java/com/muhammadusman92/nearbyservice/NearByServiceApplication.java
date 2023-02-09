package com.muhammadusman92.nearbyservice;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@SpringBootApplication
@EnableEurekaClient
public class NearByServiceApplication {
//	@Value("${app.firebase-config-file}")
	private String firebaseConfigPath="firebase-config/nearby-service-73024-firebase-adminsdk-2pd6x-704c034376.json";
	public static void main(String[] args) {
		SpringApplication.run(NearByServiceApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		ModelMapper modelMapper = new ModelMapper();
//		modelMapper.typeMap(Disease.class, DiseaseDto.class).addMappings(mapper->{
//			mapper.skip(DiseaseDto::setPrescriptionSet);
//		});
		return modelMapper;
	}
	@Bean
	FirebaseMessaging firebaseMessaging() throws IOException {
		GoogleCredentials googleCredentials = GoogleCredentials
				.fromStream(new ClassPathResource(firebaseConfigPath).getInputStream());
		FirebaseOptions firebaseOptions = FirebaseOptions
				.builder()
				.setCredentials(googleCredentials)
				.build();
		FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "NearByWeb");
		return FirebaseMessaging.getInstance(app);
	}

}
