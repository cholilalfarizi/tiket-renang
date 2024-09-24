package com.cholildev.tiket_renang.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationDTO {
    private long totalItem;
    private int totalPages;
    private int currentPage;
    private int pageSize;
}