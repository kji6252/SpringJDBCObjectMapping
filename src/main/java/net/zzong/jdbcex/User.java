package net.zzong.jdbcex;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private long id;
	private String name;
	private String email;
	private Date regdt;
}
