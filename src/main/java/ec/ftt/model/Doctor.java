package ec.ftt.model;

import java.util.Objects;

public class Doctor {

	private long id;
	private String name,
	               email,
	               crm,
	               unity;
	
	public Doctor() {
		
	}
	
	public Doctor(String id, String name, String email, String crm , String unity) {
		super();
		setId(id);
		setName(name);
		setEmail(email);
		setCrm(crm);
		setUnity(unity);
	}
	
	public Doctor(long id, String name, String email, String crm, String unity) {
		super();
		setId(id);
		setName(name);
		setEmail(email);
		setCrm(crm);
		setUnity(unity);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void setId(String id) {
		if (id.length()==0)
			setId(0);
		else
			setId(Long.valueOf(id));
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getUnity() {
		return unity;
	}

	public void setUnity(String unity) {
		this.unity = unity;
	}
	
	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", email=" + email + ", crm=" + crm + ", unity=" + unity + "]";
	}


}