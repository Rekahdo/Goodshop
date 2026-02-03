package com.rekahdo.goodshop.profile_service.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "address-service",
        url = "${application.address-service.url}",
        path = "/dev-hide/1090/addresses")
public interface AddressServiceClient {

}
