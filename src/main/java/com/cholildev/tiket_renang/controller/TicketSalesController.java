package com.cholildev.tiket_renang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cholildev.tiket_renang.dto.MessageResponseDTO;
import com.cholildev.tiket_renang.dto.ResponseBodyDTO;
import com.cholildev.tiket_renang.dto.TicketSalesRequestDTO;
import com.cholildev.tiket_renang.service.TicketSalesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/ticket-sales")
public class TicketSalesController {
    private final TicketSalesService ticketSalesService;

    @PostMapping
    public ResponseEntity<MessageResponseDTO> createTicketSales(
            @RequestBody TicketSalesRequestDTO request) {

        MessageResponseDTO responseDTO = ticketSalesService.createTicketSales(request);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<ResponseBodyDTO> getSales() {
        ResponseBodyDTO responseBodyDTO = ticketSalesService.getSalesList();
        return ResponseEntity.ok(responseBodyDTO);
    }
}
