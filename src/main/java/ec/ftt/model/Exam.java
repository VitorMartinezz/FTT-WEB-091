package ec.ftt.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Exam {

	private long id;
	private String study,
	               type,
	               date;
	
	public Exam() {
		
	}
	
	public Exam(String id, String study, String type, String date) {
		super();
		setId(id);
		setStudy(study);
		setType(type);
		setDate(date);
	}
	
	public Exam(long id, String study, String type, String date) {
		super();
		setId(id);
		setStudy(study);
		setType(type);
		setDate(date);
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

	public String getStudy() {
		return study;
	}

	public void setStudy(String study) {
		this.study = study;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "Doctor [id=" + id + ", study=" + study + ", type=" + type + ", date=" + date + "]";
	}

}