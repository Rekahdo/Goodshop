package com.rekahdo.goodshop.user_service.dtos.assemblers;

import com.rekahdo.goodshop.user_service.controllers.AppUserController;
import com.rekahdo.goodshop.user_service.dtos.entities.AppUserDto;
import com.rekahdo.goodshop.user_service.dtos.paginations.AccountPageRequest;
import com.rekahdo.goodshop.user_service.dtos.paginations.ApiPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AppUserAssembler extends ApiAssembler<AppUserDto> {

    public AppUserAssembler() {
        super(methodOn(AppUserController.class).retrieveAll(null));
    }

    @Override
    public EntityModel<AppUserDto> toModel(AppUserDto entity) {
        return EntityModel.of(entity,
                link(linkTo(methodOn(AppUserController.class).retrieveById(entity.getId())), HttpMethod.GET),
                link(linkTo(methodOn(AppUserController.class).verify(entity.getEmail())), "verify-account", HttpMethod.GET),
                link(linkTo(methodOn(AppUserController.class).delete(entity.getId())), "delete-account", HttpMethod.DELETE),
                link(linkTo(methodOn(AppUserController.class).editUsername(entity.getId(), null, null)), "edit-username", HttpMethod.PUT),
                link(linkTo(methodOn(AppUserController.class)   .editEmail(entity.getId(), null, null)), "edit-email", HttpMethod.PUT),
                link(linkTo(methodOn(AppUserController.class).editPassword(entity.getId(), null, null)), "edit-password", HttpMethod.PUT)
        );
    }

    @Override
    public CollectionModel<EntityModel<AppUserDto>> toCollectionModel(Iterable<? extends AppUserDto> entities) {
        List<EntityModel<AppUserDto>> entityModels = StreamSupport
                .stream(entities.spliterator(), false)
                .map(this::toModel).toList();

        return CollectionModel.of(entityModels,
                link(linkTo(methodOn(AppUserController.class).retrieveAll(null)), "all-users", HttpMethod.GET)
        );
    }

}