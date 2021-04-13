package net.alepuzio.restservice.server.jwt_old;

import java.util.List;

public class Authority_old {

    private Long id;

    private AuthorityName_old name;

    private List<User_old> users;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AuthorityName_old getName() {
		return name;
	}

	public void setName(AuthorityName_old name) {
		this.name = name;
	}

	public List<User_old> getUsers() {
		return users;
	}

	public void setUsers(List<User_old> users) {
		this.users = users;
	}

}