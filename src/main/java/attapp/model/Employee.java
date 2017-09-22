package attapp.model;

public class Employee {
	private String name;
	private String id;
	private String form_A_compliance ;
	private String form_B_compliance ;
	private String manager;
	private String project;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getForm_A_compliance() {
		return form_A_compliance;
	}
	public void setForm_A_compliance(String form_A_compliance) {
		this.form_A_compliance = form_A_compliance;
	}
	public String getForm_B_compliance() {
		return form_B_compliance;
	}
	public void setForm_B_compliance(String form_B_compliance) {
		this.form_B_compliance = form_B_compliance;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	
}
