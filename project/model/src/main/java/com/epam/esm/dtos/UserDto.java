package com.epam.esm.dtos;

import com.epam.esm.entities.Order;
import com.epam.esm.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends RepresentationModel<UserDto> {

    private Long id;
    private String name;
    private List<Order> orders;

    public static UserDto toDto(User user){
        return new UserDto(user.getId(), user.getName(), user.getOrders());
    }
}
