package com.sk.intensive.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@ToString
@Getter
public class ChatRoomDTO {
	
	private String chatRoomName;
	private String wgs84_x;
	private String wgs84_y;
	
}
