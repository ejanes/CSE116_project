package ratings;

import ratings.datastructures.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class DegreesOfSeperation {
    private ArrayList<Movie> movieList;

    public DegreesOfSeperation(ArrayList<Movie> movieList) {
        this.movieList = movieList;
    }

    public int degreesOfSeperation(String castMember1, String castMember2) {
        Graph graph = new Graph();
        graph.createAdjList(this.movieList);
        HashMap<String, ArrayList<String>> adjList = graph.getAdjList();

        ArrayList<String> alreadyBeenExplored = new ArrayList<>();
        HashMap<String, Integer> distancesHashMap = new HashMap<>();
        Queue<String> queue = new LinkedList<>();

        if (castMember1.equals(castMember2)) {
            return 0;
        } else {
            if (adjList.containsKey(castMember1)) {
                alreadyBeenExplored.add(castMember1);
                queue.add(castMember1);
                distancesHashMap.put(castMember1, 0);
                while (queue != null) {
                    String currentCastMember = queue.remove();
                    for (String connectedCastMember : adjList.get(currentCastMember)) {
                        if (!alreadyBeenExplored.contains(connectedCastMember)) {
                            if (adjList.get(connectedCastMember).contains(castMember2)) {
                                return distancesHashMap.get(currentCastMember) + 1;
                            } else {
                                alreadyBeenExplored.add(connectedCastMember);
                                queue.add(connectedCastMember);
                                distancesHashMap.put(connectedCastMember, distancesHashMap.get(currentCastMember) + 1);
                            }
                        }
                    }
                }
                return -1;
            } else {
                return -1;
            }
        }
    }
}
