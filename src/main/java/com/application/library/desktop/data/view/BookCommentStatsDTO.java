package com.application.library.desktop.data.view;

public class BookCommentStatsDTO {
    private Long totalComments;
    private Double averageRating;

    public BookCommentStatsDTO() {
    }

    public Long getTotalComments() {
        return totalComments;
    }

    public void setTotalComments(Long totalComments) {
        this.totalComments = totalComments;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
