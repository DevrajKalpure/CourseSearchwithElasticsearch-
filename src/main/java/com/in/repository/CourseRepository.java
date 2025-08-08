package com.in.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.in.entity.CourseDocument;

public interface CourseRepository extends ElasticsearchRepository<CourseDocument, String> {

	List<CourseDocument> findByTitleContainingOrDescriptionContaining(String keyword, String keyword2);

	List<CourseDocument> findByCategoryAndType(String category, String type);

    Page<CourseDocument> findByTitleContainingOrDescriptionContaining(String titleKeyword, String descriptionKeyword, Pageable pageable);

	Page<CourseDocument> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
		        String titleKeyword, String descriptionKeyword, Pageable pageable);
}
