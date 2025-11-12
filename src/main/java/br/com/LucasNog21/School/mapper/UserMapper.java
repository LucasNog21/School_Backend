package br.com.LucasNog21.School.mapper;

import br.com.LucasNog21.School.dto.security.AccountCredentialsDTO;
import br.com.LucasNog21.School.model.security.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    User userDTOtoUser(AccountCredentialsDTO dto);

    AccountCredentialsDTO userToUserDTO(User entity);
}
