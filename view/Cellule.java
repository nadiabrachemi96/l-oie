package view;

import Model.Coodinate;

public class Cellule {
	private Coodinate pos;
	private TypeCellule type;

	public Coodinate getPos() {
		return pos;
	}

	public void setPos(Coodinate pos) {
		this.pos = pos;
	}

	public TypeCellule getType() {
		return type;
	}

	public void setType(TypeCellule type) {
		this.type = type;
	}

	public Cellule(Coodinate pos, TypeCellule type) {
		this.pos = pos;
		this.type = type;
	}

}
