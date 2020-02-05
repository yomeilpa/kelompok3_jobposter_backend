package com.Linov.JobPoster.model;

public class ReportPerYear {

		private String title;
		private String recruiter;
		private String mulai;
		private String berakhir;
		Long acc;
		Long rj;
		Long toal;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getRecruiter() {
			return recruiter;
		}
		public void setRecruiter(String recruiter) {
			this.recruiter = recruiter;
		}
		
		public Long getAcc() {
			return acc;
		}
		public void setAcc(Long acc) {
			this.acc = acc;
		}
		public Long getRj() {
			return rj;
		}
		public void setRj(Long rj) {
			this.rj = rj;
		}
		public Long getToal() {
			return toal;
		}
		public void setToal(Long toal) {
			this.toal = toal;
		}
		public String getMulai() {
			return mulai;
		}
		public void setMulai(String mulai) {
			this.mulai = mulai;
		}
		public String getBerakhir() {
			return berakhir;
		}
		public void setBerakhir(String berakhir) {
			this.berakhir = berakhir;
		}
		
		
}
