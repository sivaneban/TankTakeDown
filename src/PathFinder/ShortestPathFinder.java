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
public class ShortestPathFinder {
    private final Map map;
    private final ClosestCollectable collectable;
    private final PathFinder pathFinder;

    public ShortestPathFinder(int mapWidth, int mapHeight, int[][] obstacleMap) {
        map = new Map(mapWidth, mapHeight, obstacleMap);
        collectable = new ClosestCollectable();
        pathFinder = new PathFinder(map, collectable);
    }

    public Path getShortestPath(int startX, int startY, int goalX, int goalY) {
        return pathFinder.calcShortestPath(startX, startY, goalX, goalY);
    }
}
