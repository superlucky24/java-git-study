package ydp.biz.complaint;

import java.util.Date;

public class ComplaintVO {
	private int comId;
	private String comTitle;
	private Date comDate;
	private int comPublic;
	private String comStatus;
	private String comContent;
	private String comAnswer;
	private String memId;
	
	public ComplaintVO() {}


	public int getComId() {
		return comId;
	}

	public void setComId(int comId) {
		this.comId = comId;
	}

	public String getComTitle() {
		return comTitle;
	}

	public void setComTitle(String comTitle) {
		this.comTitle = comTitle;
	}

	public Date getComDate() {
		return comDate;
	}

	public void setComDate(Date comDate) {
		this.comDate = comDate;
	}

	public int getComPublic() {
		return comPublic;
	}

	public void setComPublic(int comPublic) {
		this.comPublic = comPublic;
	}

	public String getComStatus() {
		return comStatus;
	}

	public void setComStatus(String comStatus) {
		this.comStatus = comStatus;
	}

	public String getComContent() {
		return comContent;
	}

	public void setComContent(String comContent) {
		this.comContent = comContent;
	}

	public String getComAnswer() {
		return comAnswer;
	}

	public void setComAnswer(String comAnswer) {
		this.comAnswer = comAnswer;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}
}