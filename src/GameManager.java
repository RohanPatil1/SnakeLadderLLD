import entities.*;

import java.util.Deque;
import java.util.LinkedList;

public class GameManager {

    Board board;
    Dice dice;
    Deque<Player> playersDeque =new LinkedList<>();
    Player winner;


    public GameManager() {
        initGame();
    }

    private void initGame() {
        board = new Board(10, 5, 5);
        dice = new Dice(1);
        winner = null;
        addPlayers();
    }

    private void addPlayers() {
        Player player1 = new Player("Rohan", 0);
        Player player2 = new Player("Raj", 0);
        playersDeque.add(player1);
        playersDeque.add(player2);
    }

    public void startGame() {

        while (winner == null) {
            //Get the current player
            Player currPlayer = findPlayerTurn();
            //Roll the dice
            int diceValue = dice.rollDice();

            System.out.println(currPlayer.getId() + " rolled :" + diceValue);

            //Set new position based on dice value
            int playerNewPosition = currPlayer.getCurrentPosition() + diceValue;
            playerNewPosition = jumpCheck(playerNewPosition);
            currPlayer.setCurrentPosition(playerNewPosition);

            System.out.println(currPlayer.getId() + "'s current position is now: " + currPlayer.getCurrentPosition());

            // Player won
            if (playerNewPosition > board.cells.length * board.cells.length - 1) {
                winner = currPlayer;
            }
        }

        System.out.println("Game Over\n Winner is " + winner.getId());
    }

    private int jumpCheck(int position) {

        //Exceeded from goal location
        if (position > board.cells.length * board.cells.length - 1) {
            return position;
        }

        Cell cell = board.getCell(position);
        Jump jumpInfo = cell.getJump();
        if (jumpInfo != null && jumpInfo.getStart() == position) {
            String jumpBy = (jumpInfo.getStart() > jumpInfo.getEnd()) ? "Ladder" : "Snake";
            System.out.println("Player got jump by " + jumpBy);
            return jumpInfo.getEnd();//player new position
        }
        return position;
    }

    private Player findPlayerTurn() {
        Player playerTurn = playersDeque.removeFirst();
        playersDeque.addLast(playerTurn);
        return playerTurn;
    }
}
