package com.Linov.JobPoster.model;

public class ReportPerYear {

		private String title;
		private String recruiter;
		private String mulai;
		private String berakhir;
		Integer acc;
		Integer rj;
		Integer toal;
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
		
		public Integer  getAcc() {
			return acc;
		}
		public void setAcc(Integer acc) {
			this.acc = acc;
		}
		public Integer getRj() {
			return rj;
		}
		public void setRj(Integer rj) {
			this.rj = rj;
		}
		public Integer getToal() {
			return toal;
		}
		public void setToal(Integer toal) {
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
