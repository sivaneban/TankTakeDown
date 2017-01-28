/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PathFinder;

/**
 *
 * @author Notebook 15
 */
public class ClosestCollectable {
    public float getEstimatedDistanceToGoal(int startX, int startY, int goalX, int goalY) {
        float dx = goalX - startX;
        float dy = goalY - startY;
        float result = (float) (dx * dx) + (dy * dy);
        return result;
    }
}
