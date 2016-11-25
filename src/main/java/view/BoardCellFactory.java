package view;

public interface BoardCellFactory {

	public BoardCell createCell(int buttonsize);

	public final BoardCellFactory standard = buttonsize -> new BoardCell(buttonsize);
	// public final BoardCellFactory standard = new BoardCellFactory() {
	// public BoardCell createCell(int buttonsize) {
	// return new BoardCell(buttonsize);
	// };
	// }

}
