package com.in.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.in.entity.CourseDocument;
import com.in.repository.CourseRepository;

import jakarta.annotation.PostConstruct;

@Service
public class DataLoaderService {
	@Autowired
	private CourseRepository courseRepository;

	@PostConstruct
	public void loadData() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		InputStream is = new ClassPathResource("sample-courses.json").getInputStream();
		List<CourseDocument> courses = Arrays.asList(mapper.readValue(is, CourseDocument[].class));
		courseRepository.saveAll(courses);
		System.out.println("Courses indexed: " + courses.size());
	}
}
