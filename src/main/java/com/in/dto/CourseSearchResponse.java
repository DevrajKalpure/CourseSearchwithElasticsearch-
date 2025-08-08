package com.in.dto;

import java.util.List;

import com.in.entity.CourseDocument;

public class CourseSearchResponse {
    private List<CourseDocument> results;

    public CourseSearchResponse(List<CourseDocument> results) {
        this.results = results;
    }

    public List<CourseDocument> getResults() {
        return results;
    }

    public void setResults(List<CourseDocument> results) {
        this.results = results;
    }
}
