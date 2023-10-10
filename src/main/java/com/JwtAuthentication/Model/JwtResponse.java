package com.JwtAuthentication.Model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JwtResponse {
	private String jwtToken;
	private String username;
}
