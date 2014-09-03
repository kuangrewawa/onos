package org.onlab.graph;

/**
 * Bellman-Ford graph search algorithm for locating shortest-paths in
 * directed graphs that may contain negative cycles.
 */
public class BellmanFordGraphSearch<V extends Vertex, E extends Edge<V>>
        extends AbstractGraphPathSearch<V, E> {

    @Override
    public Result<V, E> search(Graph<V, E> graph, V src, V dst,
                               EdgeWeight<V, E> weight) {
        checkArguments(graph, src, dst);

        // Prepare the graph search result.
        DefaultResult result = new DefaultResult(src, dst);

        // The source vertex has cost 0, of course.
        result.updateVertex(src, null, 0.0, true);

        int max = graph.getVertexes().size() - 1;
        for (int i = 0; i < max; i++) {
            // Relax, if possible, all egress edges of the current vertex.
            for (E edge : graph.getEdges()) {
                if (result.hasCost(edge.src())) {
                    result.relaxEdge(edge, result.cost(edge.src()), weight);
                }
            }
        }

        // Remove any vertexes reached by traversing edges with negative weights.
        for (E edge : graph.getEdges()) {
            if (result.hasCost(edge.src())) {
                if (result.relaxEdge(edge, result.cost(edge.src()), weight)) {
                    result.removeVertex(edge.dst());
                }
            }
        }

        // Finally, but the paths on the search result and return.
        result.buildPaths();
        return result;
    }

}