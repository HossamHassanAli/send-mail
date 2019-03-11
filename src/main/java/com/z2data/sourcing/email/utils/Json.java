package com.z2data.sourcing.email.utils;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.z2data.sourcing.email.bean.MailFeatures;

public class Json {

	private Json() {
	}

	public static MailFeatures getObjectFromJsonString(String msg) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(msg, MailFeatures.class);
	}
}
