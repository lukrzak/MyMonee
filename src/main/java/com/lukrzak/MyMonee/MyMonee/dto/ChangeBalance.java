package com.lukrzak.MyMonee.MyMonee.dto;

import com.lukrzak.MyMonee.MyMonee.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeBalance {
    private User user;
    private double changedBalance;
}
