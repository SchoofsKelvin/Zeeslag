package model;

import exception.DomainException;
/**
 * @author Daan Adams, Thomas Goris, Kelvin Schoofs, Surendra Sapkota
 *
 */
public class Player {

	private String		name;
	private int			destroyedCells;
	private final int	score	= 19;

	public Player(String name) throws DomainException {
		setName(name);
		setDestroyedCells(0);
	}

	public Player() {
		this("Nameless");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws DomainException {
		if (name == null || name.trim().isEmpty())
			throw new DomainException("The name may not be empty or null");
		this.name = name;
	}

	public int getDestroyedCells() {
		return this.destroyedCells;
	}

	public void setDestroyedCells(int s) {
		this.destroyedCells = s;
	}

	@Override
	public String toString() {
		return "Player(\"" + name + "\")";
	}

	public void addDestroyedCell() {
		this.destroyedCells++;
	}

	public int getScore() {
		return this.score - this.destroyedCells;
	}

}
