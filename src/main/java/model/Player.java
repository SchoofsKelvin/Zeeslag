package model;

import exception.DomainException;

public class Player {

	private String name;

	public Player(String name) throws DomainException {
		setName(name);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) throws DomainException {
		if (name == null || name.trim()
			.isEmpty()) { throw new DomainException("The name may not be empty or null"); }
		this.name = name;
	}

	@Override
	public String toString() {
		return "Player(\"" + name + "\"";
	}

}
