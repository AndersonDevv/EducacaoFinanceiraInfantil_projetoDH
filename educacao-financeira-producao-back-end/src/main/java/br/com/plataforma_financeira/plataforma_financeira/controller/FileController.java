package br.com.plataforma_financeira.plataforma_financeira.controller;

import java.io.IOException;
import java.util.Date;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import br.com.plataforma_financeira.plataforma_financeira.utils.FileUploadUtil;

@RestController
public class FileController {
	
	@CrossOrigin
	@PostMapping("/upload")
	public String saveImg(@RequestParam("image") MultipartFile multipartFile) {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		Date date = new Date();
		String filePrefix = date.getTime() + "-";
		String uploadDir = "files";
		
		fileName = filePrefix + fileName;
		
		try {
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("O arquivo não foi salvo");
			return "Error";
		}
		
		System.out.println("O arquivo foi salvo!");
		return "http://localhost:8085/" + uploadDir + "/" + fileName;
	}
}

