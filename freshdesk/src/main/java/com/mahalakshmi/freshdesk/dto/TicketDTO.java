package com.mahalakshmi.freshdesk.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TicketDTO {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotBlank(message = "Status is required")
    private String status;

    @NotBlank(message = "Priority is required")
    private String priority;
}