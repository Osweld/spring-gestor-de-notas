package com.osweld.dev.auth.mixin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SimpleGrantedAuthorityMixin {
	@JsonCreator
	public SimpleGrantedAuthorityMixin(@JsonProperty("authority") String role) {
		
	}

}
