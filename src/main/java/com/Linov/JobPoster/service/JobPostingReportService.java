package com.Linov.JobPoster.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.Linov.JobPoster.dao.ReportDao;
import com.Linov.JobPoster.model.DetailReport;
import com.Linov.JobPoster.model.HeaderReport;
import com.Linov.JobPoster.model.JobPostingReport;
import com.Linov.JobPoster.model.ReportPerYear;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class JobPostingReportService {

	@Autowired
	ReportDao pdDao;

	@Value("${filestorage}")
	private Path fileStorage;

	public String correctPerPackage(String reportFormat) throws FileNotFoundException, JRException {
		Path p = Paths.get(fileStorage.toString());
		if (!Files.exists(p)) {
			try {
				Files.createDirectories(p);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		List<JobPostingReport> packages = pdDao.oks();

		// load file and compile it
		File file = ResourceUtils.getFile("classpath:report/report.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(packages);
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("createdBy", "Rizal");
		String fileName = null;
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, dataSource);
		if (reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, fileStorage.toString() + "/ReportJobPosting.html");
			fileName = "ReportJobPosting.html";
		}
		if (reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, fileStorage.toString() + "/ReportJobPosting.pdf");
			fileName = "ReportJobPosting.pdf";
		}
		return fileName;
	}
	
	public String ReportPerYears(String reportFormat) throws FileNotFoundException, JRException {
//		Path p = Paths.get(fileStorage.toString());
//		if (!Files.exists(p)) {
//			try {
//				Files.createDirectories(p);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}

		//List<ReportPerYear> packages = pdDao.ReportPerYear();
		List<JobPostingReport> packages = pdDao.oks();
		File file = ResourceUtils.getFile("classpath:report/report.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(packages);
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("createdBy", "Rizal");
		String fileName = null;
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, dataSource);
		if (reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, fileStorage.toString() + "/ReportJobPosting.html");
			fileName = "ReportJobPosting.html";
		}
		if (reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, fileStorage.toString() + "/ReportJobPosting.pdf");
			fileName = "ReportJobPosting.pdf";
		}
		return fileName;
	}

	public void detail() throws FileNotFoundException, JRException {
//		Path p = Paths.get(fileStorage.toString());
//		if(!Files.exists(p)) {
//			try {
//				Files.createDirectories(p);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}

		List<DetailReport> packages = pdDao.gg();

		// load file and compile it
		File file = ResourceUtils.getFile("classpath:report/detail.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(packages);
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("createdBy", "Rizal");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, dataSource);
		JasperExportManager.exportReportToPdfFile(jasperPrint, fileStorage.toString() + "/detail.pdf");
	}

	public String header(String reportFormat, String id) throws FileNotFoundException, JRException {
//		Path p = Paths.get(fileStorage.toString());
//		if(!Files.exists(p)) {
//			try {
//				Files.createDirectories(p);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		
		detail();
		List<HeaderReport> packages = pdDao.As(id);

		// load file and compile it
		File file = ResourceUtils.getFile("classpath:report/masterReport.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(packages);
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("createdBy", "Rizal");
		String fileName = null;
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, dataSource);
		if (reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, fileStorage.toString() + "/header.html");
			fileName = "header.html";
		}
		if (reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, fileStorage.toString() + "/header.pdf");
			fileName = "header.pdf";
		}
		return fileName;
	}

	public Resource loadFileAsResource(String fileName) throws Exception {
		try {
			Path filePath = fileStorage.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new Exception("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new Exception("File not found " + fileName, ex);
		}
	}
}
