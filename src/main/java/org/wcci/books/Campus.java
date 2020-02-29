package org.wcci.books;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Campus {

	private String location;
	@Id
	@GeneratedValue
	private Long id;
	@OneToMany(mappedBy="campus")
	private Collection<Book> books;

	protected Campus() {}
	
	public Campus(String location) {
		this.location = location;

	}

	public String getLocation() {
		return location;
	}

	public Long getId() {
		
		return id;
	}

	@Override
	public String toString() {
		return "Campus [location=" + location + ", id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Campus other = (Campus) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		return true;
	}

	public Iterable<Book> getBooks() {
		return books;
	}
	

}
