package com.risk.management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.risk.management.service.DownloadTemplateService;

import org.springframework.http.*;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.*;


@RestController
@RequestMapping("/data")
public class RiskManagementController {
	
	@Autowired
	DownloadTemplateService downServ;
	
	@GetMapping("/downloadTemplate")
	public ResponseEntity<Resource> downloadTemplate(){
		System.out.println("First");
	    InputStreamResource file = new InputStreamResource(downServ.makeTemplate());

		
		return ResponseEntity.ok().
				header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=")
		        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
		        .body(file);
	}

}
