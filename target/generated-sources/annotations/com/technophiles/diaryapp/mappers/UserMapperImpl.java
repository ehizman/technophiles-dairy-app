package com.technophiles.diaryapp.mappers;

import com.technophiles.diaryapp.dtos.UserDTO;
import com.technophiles.diaryapp.models.Diary;
import com.technophiles.diaryapp.models.User;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-10T11:35:03+0100",
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
        Set<Diary> set = user.getDiaries();
        if ( set != null ) {
            userDTO.setDiaries( new HashSet<Diary>( set ) );
        }

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
        Set<Diary> set = userDTO.getDiaries();
        if ( set != null ) {
            user.setDiaries( new HashSet<Diary>( set ) );
        }

        return user;
    }
}
