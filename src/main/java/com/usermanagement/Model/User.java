package com.usermanagement.Model;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	private String FullName;
	private String Address;
	private String Image;
	private String Phone;
	private String Position;
	private String Email;
	private Date BirthDay;
	private String Password;

	public User() {

	}

	public User(int iD, String fullName, String address, String image, String phone, String position, String email,
			Date birthDay, String password) {
		super();
		ID = iD;
		FullName = fullName;
		Address = address;
		Image = image;
		Phone = phone;
		Position = position;
		Email = email;
		BirthDay = birthDay;
		Password = password;
	}

	public int getID() {
		return ID;
	}

/*	@Basic
	@Column(name = "ID")*/
	public void setID(int iD) {
		ID = iD;
	}

	public String getFullName() {
		return FullName;
	}

	@Basic
	@Column(name = "FullName")
	public void setFullName(String fullName) {
		FullName = fullName;
	}

	public String getAddress() {
		return Address;
	}

	@Basic
	@Column(name = "Address")
	public void setAddress(String address) {
		Address = address;
	}

	public String getImage() {
		return Image;
	}

	@Basic
	@Column(name = "Image")
	public void setImage(String image) {
		Image = image;
	}

	public String getPhone() {
		return Phone;
	}

	@Basic
	@Column(name = "Phone")
	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getPosition() {
		return Position;
	}

	@Basic
	@Column(name = "Position")
	public void setPosition(String position) {
		Position = position;
	}

	public String getEmail() {
		return Email;
	}

	@Basic
	@Column(name = "Email")
	public void setEmail(String email) {
		Email = email;
	}

	public Date getBirthDay() {
		return BirthDay;
	}

	@Basic
	@Column(name = "BirthDay")
	public void setBirthDay(Date birthDay) {
		BirthDay = birthDay;
	}

	public String getPassword() {
		return Password;
	}

	@Basic
	@Column(name = "Password")
	public void setPassword(String password) {
		Password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Address == null) ? 0 : Address.hashCode());
		result = prime * result + ((BirthDay == null) ? 0 : BirthDay.hashCode());
		result = prime * result + ((Email == null) ? 0 : Email.hashCode());
		result = prime * result + ((FullName == null) ? 0 : FullName.hashCode());
		result = prime * result + ID;
		result = prime * result + ((Image == null) ? 0 : Image.hashCode());
		result = prime * result + ((Password == null) ? 0 : Password.hashCode());
		result = prime * result + ((Phone == null) ? 0 : Phone.hashCode());
		result = prime * result + ((Position == null) ? 0 : Position.hashCode());
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
		User other = (User) obj;
		if (Address == null) {
			if (other.Address != null)
				return false;
		} else if (!Address.equals(other.Address))
			return false;
		if (BirthDay == null) {
			if (other.BirthDay != null)
				return false;
		} else if (!BirthDay.equals(other.BirthDay))
			return false;
		if (Email == null) {
			if (other.Email != null)
				return false;
		} else if (!Email.equals(other.Email))
			return false;
		if (FullName == null) {
			if (other.FullName != null)
				return false;
		} else if (!FullName.equals(other.FullName))
			return false;
		if (ID != other.ID)
			return false;
		if (Image == null) {
			if (other.Image != null)
				return false;
		} else if (!Image.equals(other.Image))
			return false;
		if (Password == null) {
			if (other.Password != null)
				return false;
		} else if (!Password.equals(other.Password))
			return false;
		if (Phone == null) {
			if (other.Phone != null)
				return false;
		} else if (!Phone.equals(other.Phone))
			return false;
		if (Position == null) {
			if (other.Position != null)
				return false;
		} else if (!Position.equals(other.Position))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [ID=" + ID + ", FullName=" + FullName + ", Address=" + Address + ", Image=" + Image + ", Phone="
				+ Phone + ", Position=" + Position + ", Email=" + Email + ", BirthDay=" + BirthDay + ", Password="
				+ Password + "]";
	}

}
