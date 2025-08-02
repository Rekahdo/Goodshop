package com.rekahdo.ecommerce.goodshop._dtos.records;

import lombok.Builder;

@Builder
public record MailBody(String to, String subject, String text) {
}
