package td.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the AUTHOR database table.
 * 
 */
@Entity
public class Author implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="FIRST_NAME")
	private String firstName;

	@Column(name="LAST_NAME")
	private String lastName;

    public Author() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + "]";
	}
	
	

}