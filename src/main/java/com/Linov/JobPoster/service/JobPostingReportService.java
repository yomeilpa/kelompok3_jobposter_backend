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

import com.Linov.JobPoster.dao.JobPostingDao;
import com.Linov.JobPoster.dao.ReportDao;
import com.Linov.JobPoster.model.CandidateModel;
import com.Linov.JobPoster.model.DetailReport;
import com.Linov.JobPoster.model.HeaderReport;
import com.Linov.JobPoster.model.JobPostingReport;
import com.Linov.JobPoster.model.ReportPerYear;
import com.Linov.JobPoster.model.ReportbyPoster;

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
	
	@Autowired
	JobPostingDao jobs;
	
	@Autowired
	CandiateService cd;
	
	
	


	@Value("${filestorage}")
	private Path fileStorage;


	
	public String ReportPerYears(String reportFormat,String year) throws FileNotFoundException, JRException {
//		Path p = Paths.get(fileStorage.toString());
//		if (!Files.exists(p)) {
//			try {
//				Files.createDirectories(p);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}

		//List<ReportPerYear> packages = pdDao.ReportPerYear();
		List<ReportPerYear> packages = jobs.findforReport(year);
		File file = ResourceUtils.getFile("classpath:report/reporttahunan.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(packages);
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("createdBy", "Rizal");
		String fileName = null;
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, dataSource);
		if (reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, fileStorage.toString() + "/"+"Report-"+year+".html");
			fileName = "Report-"+year+".html";
		}
		if (reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, fileStorage.toString() + "/"+"Report-"+year+".pdf");
			fileName = "Report-"+year+".pdf";
		}
		return fileName;
	}
	
	public String ReportbyHr(String reportFormat,String id) throws FileNotFoundException, JRException {
//		Path p = Paths.get(fileStorage.toString());
//		if (!Files.exists(p)) {
//			try {
//				Files.createDirectories(p);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}

		//List<ReportPerYear> packages = pdDao.ReportPerYear();
		CandidateModel cs = cd.findById(id);
		String name = cs.getName();
		List<ReportbyPoster> packages = jobs.findforReportCd(id);
		File file = ResourceUtils.getFile("classpath:report/hr.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(packages);
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("createdBy", "Rizal");
		String fileName = null;
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, dataSource);
		if (reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, fileStorage.toString() + "/"+"ReportJob-"+name+".html");
			fileName = "ReportJob-"+name+".html";
		}
		if (reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, fileStorage.toString() + "/"+"ReportJob-"+name+".pdf");
			fileName = "ReportJob-"+name+".pdf";
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
