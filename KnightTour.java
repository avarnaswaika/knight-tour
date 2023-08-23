
import java.util.Stack;

// Do you know the chess piece that looks like a horse? It's called a "Knight",
// and the "Knight's Tour" is a famous problem where you want the Knight to move
// around a chess board such that it visits every position on the board exactly once.
// Recall that the Knight can move in the shape of the letter "L" in any direction
// IMPORTANT: See our A3 handout for examples and more detailed instructions/hints!

public class KnightTour {
    // NOTE: There can be several distinct valid tours; your job is to find
    // and print only one valid tour (the first valid one you discover).
    // Please do NOT attempt to discover all possible valid tours! To get
    // a sense of how many valid board configurations exist for different
    // board sizes, see "The Knight's Paths" table here:
    // http://www.behnel.de/knight.html

    /** tour method is where you add your code for implementing
    * a Knight Tour's solution for an n*n chess board
    * @param n size of the board
    * @return KnightBoard object with a valid Knight Tour
    */
    public static KnightBoard tour(int n){
      int Xfirst []={2,1,-1,-2,-2,-1,1,2};//store all possible x co-odinates chnage values in clockwise order
      int Ysecond []={1,2,2,1,-1,-2,-2,-1};//store all possible y co-odinates chnage values in clockwise order
      // Your solution must utilize the stack "candidates" below,
      // to keep track of different possible sequences of Knight moves
      Stack<KnightBoard> candidates = new Stack<KnightBoard>();//creating a stack
      KnightBoard kb = new KnightBoard(n); // create initial board of size n*n
      candidates.push(kb); // push the initial board onto the stack
      while (!candidates.empty())//checking if stack is empty
      {
        kb=candidates.pop();//removing the last inserted move from the stack
        if(kb.getMoveCount() == n*n)//checking if all the sqaures on the board have been covered or not
        {
          return kb.copyBoard();//since all sqaures have been covered returning a copy of the board
        }
        else
        {

          for(int i=0;i<Ysecond.length;i++)//checking all possible moves in clokcwise order
          {
            KnightBoard copykb= kb.copyBoard();
            if( copykb.move(copykb.getCurrentX() + Xfirst[i], copykb.getCurrentY() + Ysecond[i]))//seeing if the move is valid or not
            candidates.push(copykb);//adding the valid move to the stack
          }
        }
      }
      if(candidates.empty())//checking if stack is empty
      return kb.copyBoard();//returning the board as all possible moves have been made
      return kb.copyBoard();
    }


    // Do NOT modify this main method. If you need to add code for
    // testing your solution, just make sure to comment it out before submission
    public static void main(String[] args) {
      int n = 8; // default board size if user didn't specify
      // pass in parameter n from command line
      if (args.length == 1) {
          n = Integer.parseInt(args[0].trim());
          if (n < 3 || n > 8) {
              System.out.println("Incorrect parameter (n must be >= 3 and <= 8)");
              System.exit(-1);
          }
      }
      long startTime = System.nanoTime();
      KnightBoard winner = KnightTour.tour(n);
      long endTime = System.nanoTime();
      double delta = (endTime - startTime) / 1e6;
      // Display the solution you discovered:
      System.out.println("\nPossible Knight Tour with max #squares visited in this ("+n+"x" +n+") board:");
      winner.printChessBoard();
      System.out.println("\n(Time to find this solution = " + delta + " milliseconds)");
    }
}
