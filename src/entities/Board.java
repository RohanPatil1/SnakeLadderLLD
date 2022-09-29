package entities;

import java.util.concurrent.ThreadLocalRandom;

public class Board {
    public Cell[][] cells;

    //Constructor
    public Board(int boardSize, int snakeCount, int ladderCount) {
        initCells(boardSize);
        addSnakes(cells,snakeCount);
        addLadders(cells,ladderCount);
    }

    //Adds Cell obj to cell matrix
    private void initCells(int boardSize) {
        cells = new Cell[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                Cell cell = new Cell();
                cells[i][j] = cell;
            }
        }
    }

    private void addSnakes(Cell[][] cells, int snakeCount) {

        while (snakeCount > 0) {
            int snakeStartPosition = ThreadLocalRandom.current().nextInt(1, cells.length * cells.length - 1);
            int snakeEndPosition = ThreadLocalRandom.current().nextInt(1, cells.length * cells.length - 1);
            if (snakeEndPosition >= snakeStartPosition) {
                //Invalid random values
                continue;
            }

            //Got valid start & end positions
            Jump snakeJumpInfo = new Jump(snakeStartPosition, snakeEndPosition);
            Cell snakeCell = getCell(snakeStartPosition);
            snakeCell.setJump(snakeJumpInfo);

            snakeCount--;
        }
    }

    private void addLadders(Cell[][] cells, int ladderCount) {

        while (ladderCount > 0) {
            int ladderStartPosition = ThreadLocalRandom.current().nextInt(1, cells.length * cells.length - 1);
            int ladderEndPosition = ThreadLocalRandom.current().nextInt(1, cells.length * cells.length - 1);
            if (ladderStartPosition >= ladderEndPosition) {
                //Invalid random values
                continue;
            }

            //Got valid start & end positions
            Jump ladderJumpInfo = new Jump(ladderStartPosition, ladderEndPosition);
            Cell ladderCell = getCell(ladderStartPosition);
            ladderCell.setJump(ladderJumpInfo);

            ladderCount--;
        }
    }

    public Cell getCell(int position) {
        int boardRow = position / cells.length;
        int boardColumn = (position % cells.length);
        return cells[boardRow][boardColumn];
    }

}
