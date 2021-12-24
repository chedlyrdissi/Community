package com.community.community.usermanagement.assembler;

import com.community.community.usermanagement.entity.User;
import com.community.community.usermanagement.representation.UserRepresentation;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import java.util.stream.Collectors;

public class UserModelAssembler extends RepresentationModelAssemblerSupport<User, UserRepresentation> {

    /**
     * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and resource type.
     *
     * @param controllerClass must not be {@literal null}.
     * @param resourceType    must not be {@literal null}.
     */
    public UserModelAssembler(Class<?> controllerClass, Class<UserRepresentation> resourceType) {
        super(controllerClass, resourceType);
    }

    @Override
    public UserRepresentation toModel(User entity) {
        UserRepresentation user = instantiateModel(entity);
        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setRoles(entity.getRoles().stream().map(userRole -> userRole.getRole()).collect(Collectors.toList()));
        return user;
    }
}
