package com.happypet.HappyPet.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paginated<T> {

    private List<T> results;
    private int currentPage;
    private int resultsCurrentPage;
    private int totalPages;
    private long totalResults;
}
