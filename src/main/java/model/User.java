package main.java.model;

<<<<<<< HEAD
public class User {
	
	private int u_id;
	private String name;
	private String email;
=======
public class User implements IModel{
>>>>>>> dev

	public User() {
		
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
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
	
}
