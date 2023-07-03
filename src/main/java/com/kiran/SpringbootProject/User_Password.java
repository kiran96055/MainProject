package com.kiran.SpringbootProject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User_Password {
	private String email;
	private String password;
	private String confPassword;
}
