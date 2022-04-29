package com.technophiles.diaryapp.mappers;

import com.technophiles.diaryapp.dtos.UserDTO;
import com.technophiles.diaryapp.models.User;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-29T11:22:26+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Eclipse Adoptium)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO userToUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setEmail( user.getEmail() );

        return userDTO;
    }

    @Override
    public User userDTOToUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDTO.getId() );
        user.setEmail( userDTO.getEmail() );

        return user;
    }
}
