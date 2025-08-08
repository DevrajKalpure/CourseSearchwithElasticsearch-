package com.in.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.in.entity.CourseDocument;
import com.in.repository.CourseRepository;

@Service
public class CourseSearchService {

	@Autowired
	private CourseRepository courseRepository;

    public List<CourseDocument> search(String q, String category, String type,
                                       Integer minAge, Integer maxAge,
                                       Double minPrice, Double maxPrice,
                                       Instant startDate,
                                       String sort,
                                       int page, int size) {

        Pageable pageable = getPageable(sort, page, size);

        // Simple keyword search for demo; for full filters, you would implement a custom query

        if (q == null || q.isBlank()) {
            // Return all paginated
            return courseRepository.findAll(pageable).getContent();
        }

        // For this example, search by keyword only.
        return courseRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(q, q, pageable).getContent();
    }

    private Pageable getPageable(String sort, int page, int size) {
        Sort sortObj;
        if ("priceAsc".equalsIgnoreCase(sort)) {
            sortObj = Sort.by(Sort.Direction.ASC, "price");
        } else if ("priceDesc".equalsIgnoreCase(sort)) {
            sortObj = Sort.by(Sort.Direction.DESC, "price");
        } else {
            // default upcoming: sort by nextSessionDate ascending
            sortObj = Sort.by(Sort.Direction.ASC, "nextSessionDate");
        }
        return PageRequest.of(page, size, sortObj);
    }

	public List<CourseDocument> getAllCourses() {
		return StreamSupport.stream(courseRepository.findAll().spliterator(), false).collect(Collectors.toList());
	}

	public List<CourseDocument> searchByCategory(String category) {
		return StreamSupport.stream(courseRepository.findAll().spliterator(), false)
				.filter(course -> course.getCategory().equalsIgnoreCase(category)).collect(Collectors.toList());
	}

	public List<CourseDocument> filterByAgeRange(int age) {
		return StreamSupport.stream(courseRepository.findAll().spliterator(), false)
				.filter(course -> age >= course.getMinAge() && age <= course.getMaxAge()).collect(Collectors.toList());
	}

	public Optional<CourseDocument> getCourseById(String id) {
		return courseRepository.findById(id);
	}

	public List<CourseDocument> searchByKeyword(String keyword) {
		return StreamSupport.stream(courseRepository.findAll().spliterator(), false)
				.filter(course -> course.getTitle().toLowerCase().contains(keyword.toLowerCase())
						|| course.getDescription().toLowerCase().contains(keyword.toLowerCase()))
				.collect(Collectors.toList());
	}
}
