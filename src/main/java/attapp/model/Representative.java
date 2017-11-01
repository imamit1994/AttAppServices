package attapp.model;

import org.springframework.web.multipart.MultipartFile;

public class Representative {
	private String name;
	private String uuid;
	private String supervisorName;
	private String date;
	private MultipartFile files;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getSupervisorName() {
		return supervisorName;
	}
	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public MultipartFile getFiles() {
		return files;
	}
	public void setFiles(MultipartFile files) {
		this.files = files;
	}
}
