package com.in.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.in.entity.CourseDocument;

public interface CourseRepository extends ElasticsearchRepository<CourseDocument, String> {

	Page<CourseDocument> findByCategoryIgnoreCase(String category, Pageable pageable);

	Page<CourseDocument> findByCategoryIgnoreCaseAndTypeIgnoreCase(String category, String type, Pageable pageable);

	Page<CourseDocument> findByCategoryIgnoreCaseAndTypeIgnoreCaseAndMinAgeLessThanEqualAndMaxAgeGreaterThanEqual(
			String category, String type, int maxAge, int minAge, Pageable pageable);

	Page<CourseDocument> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title,
			String description, Pageable pageable);

	List<CourseDocument> findByTitleContainingOrDescriptionContaining(String keyword, String keyword2);

	List<CourseDocument> findByCategoryAndType(String category, String type);

	Page<CourseDocument> findByTitleContainingOrDescriptionContaining(String titleKeyword, String descriptionKeyword,
			Pageable pageable);

	Page<CourseDocument> findByNextSessionDateGreaterThanEqual(Instant date, Pageable pageable);

}
