package com.sk.intensive.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@ToString
public class ChatRoomDTO {
	
	private String chatRoomName;
	private float wgs84_x;
	private float wgs84_y;
	
}
