package com.z2data.sourcing.email.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.z2data.sourcing.email.bean.MailFeatures;
import com.z2data.sourcing.email.service.SendMailService;
import com.z2data.sourcing.email.utils.Json;

@RestController
@RequestMapping(value = "/email")
public class SendMailController {
	private static final Logger logger = LoggerFactory.getLogger(SendMailController.class);

	@Autowired
	private SendMailService service;

	@PostMapping("/sendEmail")
	@ResponseBody
	public String sendMail(@RequestBody String reqBody) {

		try {
			MailFeatures obj = Json.getObjectFromJsonString(reqBody);
			service.sendMail(obj);
			return "Successfully Send Mail ";
		} catch (Exception e) {
			logger.error("err", e);
			return e.toString();
		}

	}
}