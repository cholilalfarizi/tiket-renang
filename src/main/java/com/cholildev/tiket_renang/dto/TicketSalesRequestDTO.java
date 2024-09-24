package com.cholildev.tiket_renang.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketSalesRequestDTO {
    private int quantity;
    private Long ticketId;
    private Long customerId;
    private Date visitDate;
}
