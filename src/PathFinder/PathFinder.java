/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PathFinder;

import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author Notebook 15
 */
public class PathFinder {
    private final Map map;
    private final ClosestCollectable collectable;
    private final ArrayList<Node> closedList;
    private final SortedNodeList openList;

    PathFinder(Map map, ClosestCollectable collectable) {
        this.map = map;
        this.collectable = collectable;
        closedList = new ArrayList<>();
        openList = new SortedNodeList();
    }

    public Path calcShortestPath(int startX, int startY, int goalX, int goalY) {
        map.setStartLocation(startX, startY);
        map.setGoalLocation(goalX, goalY);
        if (map.getNode(goalX, goalY).isObstacle) {
            return null;
        }

        map.getStartNode().setDistanceFromStart(0);
        closedList.clear();
        openList.clear();
        openList.add(map.getStartNode());

        while (openList.size() != 0) {
            Node current = openList.getFirst();

            if (current.getX() == map.getGoalLocationX() && current.getY() == map.getGoalLocationY()) {
                return reconstructPath(current);
            }
            openList.remove(current);
            closedList.add(current);

            for (Node neighbor : current.getNeighborList()) {
                boolean neighborIsBetter;

                if (closedList.contains(neighbor)) {
                    continue;
                }

                if (!neighbor.isObstacle) {

                    float neighborDistanceFromStart = (current.getDistanceFromStart() + map.getDistanceBetween(current, neighbor));

                    if (!openList.contains(neighbor)) {
                        openList.add(neighbor);
                        neighborIsBetter = true;
                    } else {
                        neighborIsBetter = neighborDistanceFromStart < current.getDistanceFromStart();
                    }
                    if (neighborIsBetter) {
                        neighbor.setPreviousNode(current);
                        neighbor.setDistanceFromStart(neighborDistanceFromStart);
                        neighbor.setHeuristicDistanceFromGoal(collectable.getEstimatedDistanceToGoal(neighbor.getX(), neighbor.getY(), map.getGoalLocationX(), map.getGoalLocationY()));
                    }
                }

            }
        }
        return null;
    }

    private Path reconstructPath(Node node) {
        Path path = new Path();
        while (!(node.getPreviousNode() == null)) {
            path.prependWayPoint(node);
            node = node.getPreviousNode();
        }
        return path;
    }

    private class SortedNodeList {

        private final ArrayList<Node> list = new ArrayList<>();

        public Node getFirst() {
            return list.get(0);
        }

        public void clear() {
            list.clear();
        }

        public void add(Node node) {
            list.add(node);
            Collections.sort(list);
        }

        public void remove(Node n) {
            list.remove(n);
        }

        public int size() {
            return list.size();
        }

        public boolean contains(Node n) {
            return list.contains(n);
        }
    }

}
