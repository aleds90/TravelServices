package com.ds.travel.model;
// Generated Oct 7, 2016 12:53:25 PM by Hibernate Tools 5.1.0.Alpha1

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;




/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable, UserDetails {

	private Integer id;
	private Profile profile;
	private String username;
	private String email;
	private String password;
	private Date createdAt;
	private Date updatedAt;
	@JsonIgnore private Set travels = new HashSet(0);
	@JsonIgnore private Set countryUsers = new HashSet(0);
	@JsonIgnore private Set messages = new HashSet(0);

	/* Spring Security related fields*/
	@JsonIgnore private List<Role> roles;
	@JsonIgnore private List<Role> authorities;
	@JsonIgnore private boolean accountNonExpired = true;
	@JsonIgnore private boolean accountNonLocked = true;
	@JsonIgnore private boolean credentialsNonExpired = true;
	@JsonIgnore private boolean enabled = true;
    
	public User() {
	}

	public User(Profile profile, String username, String email, String password, Date createdAt, Date updatedAt,
			Set travels, Set countryUsers, Set messages) {
		this.profile = profile;
		this.username = username;
		this.email = email;
		this.password = password;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.travels = travels;
		this.countryUsers = countryUsers;
		this.messages = messages;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Set getTravels() {
		return this.travels;
	}

	public void setTravels(Set travels) {
		this.travels = travels;
	}

	public Set getCountryUsers() {
		return this.countryUsers;
	}

	public void setCountryUsers(Set countryUsers) {
		this.countryUsers = countryUsers;
	}

	public Set getMessages() {
		return this.messages;
	}

	public void setMessages(Set messages) {
		this.messages = messages;
	}
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public List<Role> getAuthorities() {
        return authorities;
    }
    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }
    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }
    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }
    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}