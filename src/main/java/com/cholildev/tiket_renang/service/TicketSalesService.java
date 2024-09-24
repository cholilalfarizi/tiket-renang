package com.cholildev.tiket_renang.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cholildev.tiket_renang.dto.MessageResponseDTO;
import com.cholildev.tiket_renang.dto.ResponseBodyDTO;
import com.cholildev.tiket_renang.dto.TicketSalesDTO;
import com.cholildev.tiket_renang.dto.TicketSalesRequestDTO;
import com.cholildev.tiket_renang.model.Customer;
import com.cholildev.tiket_renang.model.Ticket;
import com.cholildev.tiket_renang.model.TicketSales;
import com.cholildev.tiket_renang.repository.CustomerRepository;
import com.cholildev.tiket_renang.repository.TicketRepository;
import com.cholildev.tiket_renang.repository.TicketSalesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TicketSalesService {
    private final TicketSalesRepository ticketSalesRepository;
    private final CustomerRepository customerRepository;
    private final TicketRepository ticketRepository;

    public MessageResponseDTO createTicketSales(TicketSalesRequestDTO request) {
        try {
            Optional<Customer> customerOpt = customerRepository.findById(request.getCustomerId());

            if (!customerOpt.isPresent()) {
                String message = "Customer dengan id tersebut tidak ditemukan";
                return new MessageResponseDTO(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(),
                        message);
            }

            Customer customer = customerOpt.get();

            Optional<Ticket> ticketOpt = ticketRepository.findById(request.getTicketId());

            if (!ticketOpt.isPresent()) {
                String message = "Ticket dengan id tersebut tidak ditemukan";
                return new MessageResponseDTO(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(),
                        message);
            }

            Ticket ticket = ticketOpt.get();

            long totalPrice = request.getQuantity() * ticket.getPrice();

            TicketSales ticketSales = TicketSales.builder()
                    .customer(customer)
                    .ticket(ticket)
                    .quantity(request.getQuantity())
                    .totalAmount(totalPrice)
                    .saleDate(Date.valueOf(LocalDate.now()))
                    .visitDate(request.getVisitDate())
                    .build();

            ticketSalesRepository.save(ticketSales);

            String message = "Berhasil menambahkan penjualan";

            return new MessageResponseDTO(HttpStatus.OK.name(), HttpStatus.OK.value(),
                    message);
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Internal Server Error");
        }
    }

    public ResponseBodyDTO getSalesList() {
        try {

            List<TicketSales> salesList = ticketSalesRepository.findAll();

            List<TicketSalesDTO> response = salesList.stream().map(sales -> {
                TicketSalesDTO dto = TicketSalesDTO.builder()
                        .salesId(sales.getSalesId())
                        .salesDate(sales.getSaleDate())
                        .visitDate(sales.getVisitDate())
                        .customerName(sales.getCustomer().getCustomerName())
                        .ticketType(sales.getTicket().getType())
                        .ticketPrice(sales.getTicket().getPrice())
                        .quantity(sales.getQuantity())
                        .totalPrice(sales.getTotalAmount())
                        .build();

                return dto;
            }).collect(Collectors.toList());

            return new ResponseBodyDTO(HttpStatus.OK.name(),
                    HttpStatus.OK.value(),
                    "Berhasil memuat data oenjualan", response);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBodyDTO(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Internal Server Error", null);
        }
    }
}
