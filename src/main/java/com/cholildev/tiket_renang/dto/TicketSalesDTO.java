package com.cholildev.tiket_renang.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketSalesDTO {
    private Long salesId;
    private Date salesDate;
    private Date visitDate;
    private String customerName;
    private String ticketType;
    private Integer ticketPrice;
    private Integer quantity;
    private Long totalPrice;

}
