package com.application.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ChangePasswordForm {
	
	@NotNull
	private Long id;
	
	@NotBlank(message="Current Password must not be blank")
	private String actual;

	@NotBlank(message="New Password must not be blank")
	private String nuevo;

	@NotBlank(message="New Password must not be blank")
	private String confirmar;
	

	public ChangePasswordForm() { }
	public ChangePasswordForm(Long id) {this.id = id;}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getActual() {
		return actual;
	}
	public void setActual(String actual) {
		this.actual = actual;
	}
	public String getNuevo() {
		return nuevo;
	}
	public void setNuevo(String nuevo) {
		this.nuevo = nuevo;
	}
	public String getConfirmar() {
		return confirmar;
	}
	public void setConfirmar(String confirmar) {
		this.confirmar = confirmar;
	}
	
	@Override
	public String toString() {
		return "ChangePasswordForm [id=" + id + ", actual=" + actual + ", nuevo=" + nuevo + ", confirmar=" + confirmar
				+ "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actual == null) ? 0 : actual.hashCode());
		result = prime * result + ((confirmar == null) ? 0 : confirmar.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nuevo == null) ? 0 : nuevo.hashCode());
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
		ChangePasswordForm other = (ChangePasswordForm) obj;
		if (actual == null) {
			if (other.actual != null)
				return false;
		} else if (!actual.equals(other.actual))
			return false;
		if (confirmar == null) {
			if (other.confirmar != null)
				return false;
		} else if (!confirmar.equals(other.confirmar))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nuevo == null) {
			if (other.nuevo != null)
				return false;
		} else if (!nuevo.equals(other.nuevo))
			return false;
		return true;
	}
}