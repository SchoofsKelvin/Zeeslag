package view;

public interface BoardCellFactory {

	public BoardCell createCell(int buttonsize);

	public final BoardCellFactory standard = buttonsize -> new BoardCell(buttonsize);

}
