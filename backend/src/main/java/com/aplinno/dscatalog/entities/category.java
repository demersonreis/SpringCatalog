package com.aplinno.dscatalog.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author demerson reis
 * @data   14/03/2022
 *
 */
public class category implements Serializable {
	
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
  private Long id; 
  private String name;
  
  public category() {
	  
  }

  
public category(Long id, String name) {
	this.id = id;
	this.name = name;
}


public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


@Override
public int hashCode() {
	return Objects.hash(id);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	category other = (category) obj;
	return Objects.equals(id, other.id);
}
 
}
