import Parameters.Parameters.HORIZONTAL_DIRECTION;
import Parameters.Parameters.VERTICAL_DIRECTION;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: jelint11
 * Date: 27.4.13
 * Time: 16:50
 *
 */


public class Move implements Serializable {

    private int sourceRow;
    private int sourceCol;
    private VERTICAL_DIRECTION verticalDir;
    private HORIZONTAL_DIRECTION horizontalDir;


    /**
     * Constructor
     * @param sRow
     * @param sCol
     * @param vDir
     * @param hDir
     */
    public Move(int sRow, int sCol, VERTICAL_DIRECTION vDir, HORIZONTAL_DIRECTION hDir) {
        sourceRow = sRow;
        sourceCol = sCol;
        verticalDir = vDir;
        horizontalDir = hDir;
    }


    /**
     * Get the row of the starting  move position.
     * @return sourceRow
     */
    public int getSourceRow() {
        return sourceRow;
    }

    /**
     * Get the column of the starting move position.
     * @return sourceColumn
     */
    public int getSourceColumn() {
        return sourceCol;
    }

    /**
     * Compute the row of the target move position.
     * @return targetRow
     */
    public int getTargetRow() {
        int targetRow = sourceRow;
        if (verticalDir == VERTICAL_DIRECTION.DOWN) {
            targetRow++;
        } else {
            targetRow--;
        }
        return targetRow;
    }

    /**
     * Compute the row of the target jump position.
     * @return targetRow
     */
    public int getTargetJumpRow() {
        int targetRow = sourceRow;
        if (verticalDir == VERTICAL_DIRECTION.DOWN) {
            targetRow = targetRow + 2;
        } else {
            targetRow = targetRow - 2;
        }
        return targetRow;
    }

    /**
     * Compute the column of the target move position.
     * @return targetColumn
     */
    public int getTargetColumn() {
        int targetColumn=sourceCol;
        if (horizontalDir == HORIZONTAL_DIRECTION.LEFT) {
            targetColumn--;
        } else {
            targetColumn++;
        }
        return targetColumn;
    }

    /**
     * Compute the column of the target jump position.
     * @return targetColumn
     */
    public int getTargetJumpColumn() {
        int targetColumn=sourceCol;
        if (horizontalDir == HORIZONTAL_DIRECTION.LEFT) {
            targetColumn = targetColumn - 2;
        } else {
            targetColumn = targetColumn + 2;
        }
        return targetColumn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Move)) return false;

        Move move = (Move) o;

        if (sourceCol != move.sourceCol) return false;
        if (sourceRow != move.sourceRow) return false;
        if (horizontalDir != move.horizontalDir) return false;
        if (verticalDir != move.verticalDir) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sourceRow;
        result = 31 * result + sourceCol;
        result = 31 * result + verticalDir.hashCode();
        result = 31 * result + horizontalDir.hashCode();
        return result;
    }


    @Override
    public String toString() {
        return "Moving " + verticalDir.toString() + " and " + horizontalDir.toString() + " from (" + sourceRow + "," +
                sourceCol + ") ";
    }

}