// Java implementierung des Dijkstra Algorithmus
// mittels einer Prioritätswarteschlange (PriorityQueue)
// Quelle: https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-in-java-using-priorityqueue/
import org.jetbrains.annotations.NotNull;

import java.sql.SQLOutput;
import java.util.*;

public class DPQ {
    private int dist[];
    private Set<Integer> settled;
    private PriorityQueue<Node> pq;
    private int V; // Anzahl der Knoten
    List<List<Node> > adj;

    public DPQ(int V)
    {
        this.V = V;
        dist = new int[V];
        settled = new HashSet<Integer>();
        pq = new PriorityQueue<Node>(V, new Node());
    }

    // Funktion des Dijkstra Algorithmus
    public void dijkstra(List<List<Node> > adj, int src)
    {
        this.adj = adj;

        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;

        // Startknoten zur Warteschlange hinzufuegen
        pq.add(new Node(src, 0));

        // Abstand zum Startknoten == 0
        dist[src] = 0;
        while (settled.size() != V) {

            // Knoten mit der geringsten Distanz
            // von der Prioritätswarteschlange entfernen
            int u = pq.remove().node;

            // Knoten mit finalisierter Distanz hinzufügen
            settled.add(u);

            e_Neighbours(u);
        }
    }

    // Nachbarn des uebergebenen Knoten durcharbeiten
    private void e_Neighbours(int u)
    {
        int edgeDistance = -1;
        int newDistance = -1;

        // Alle Nachbarn von v
        for (int i = 0; i < adj.get(u).size(); i++) {
            Node v = adj.get(u).get(i);

            // Wenn Knoten noch nicht verarbeitet wurde
            if (!settled.contains(v.node)) {
                edgeDistance = v.cost;
                newDistance = dist[u] + edgeDistance;

                // Wenn neue Distanz kostenguenstiger
                if (newDistance < dist[v.node])
                    dist[v.node] = newDistance;

                // Aktuelle Knoten zur Warteschlange hinzufuegen
                pq.add(new Node(v.node, dist[v.node]));
            }
        }
    }

    public static void main(String arg[])
    {
        int V = 10; // hier aendern
        int source = 0; // startpunkt

        // Adjazenzliste
        List<List<Node> > adj = new ArrayList<List<Node> >();

        // Liste fuer jeden Knoten initialisieren
        for (int i = 0; i < V; i++) {
            List<Node> item = new ArrayList<Node>();
            adj.add(item);
        }

        //aufgabeEins(adj); // Beschreibung siehe Methode
        //aufgabeZwei(adj); // Beschreibung siehe Methode

        // Kuerzesten Weg berechnen
        DPQ dpq = new DPQ(V);
        dpq.dijkstra(adj, source);

        // Kuerzesten Weg vom Startpunkt ausdrucken
        System.out.println("The shorted path from node :");
        for (int i = 0; i < dpq.dist.length; i++)
            System.out.println(source + " to " + i + " is "
                    + dpq.dist[i]);
    }

    /**
     * TODO Aufgabe 1
     * Adjazenzliste welche zuvor erstellt wurde einprogrammieren
     * @param adj
     *
     * Beispiel Input für den DPQ Graphen
     * adj.get(0).add(new Node(1, 9));
     * adj.get(0).add(new Node(2, 6));
     * adj.get(0).add(new Node(3, 5));
     * adj.get(0).add(new Node(4, 3));
     * adj.get(2).add(new Node(1, 2));
     * adj.get(2).add(new Node(3, 4));
     */
    private static void aufgabeEins(@NotNull List<List<Node> > adj) {
        // TODO ADD CODE HERE
        adj.get(0).add(new Node(1,5));
        adj.get(0).add(new Node(2,10));
        adj.get(1).add(new Node(0,5));
        adj.get(1).add(new Node(3,4));
        adj.get(2).add(new Node(0,10));
        adj.get(2).add(new Node(6,5));
        adj.get(3).add(new Node(1,4));
        adj.get(3).add(new Node(4,2));
        adj.get(3).add(new Node(6,6));
        adj.get(4).add(new Node(3,2));
        adj.get(4).add(new Node(5,3));
        adj.get(5).add(new Node(4,3));
        adj.get(5).add(new Node(7,9));
        adj.get(5).add(new Node(8,12));
        adj.get(5).add(new Node(9,15));
        adj.get(6).add(new Node(2,5));
        adj.get(6).add(new Node(3,6));
        adj.get(6).add(new Node(7,9));
        adj.get(7).add(new Node(6,9));
        adj.get(7).add(new Node(5,9));
        adj.get(8).add(new Node(5,12));
        adj.get(9).add(new Node(5,15));
    }

    /**
     * TODO Aufgabe 2
     * Teil 1: Konsolenanwendung entwickeln, mit welcher es möglich ist,
     * einen Graphen wie in Aufgabe 1 über die Kommandozeile einzutragen.
     *
     * Teil 2: Es soll möglich sein, mittels eines Kommandos den Graphen auszugeben
     * (entweder eine Adjazenzliste oder eine grafische Darstellung)
     *
     * Teil 3: Eine zusaetzliche Funktion implementieren, wie z.B. das entfernen eines zuvor eingetragenen
     * Kante
     *
     * Teil 4: Sollte noch Zeit uebrig sein, eine weitere Funktion deiner Wahl implementieren.
     * Fehlerbehandlung bei falschen Input, Parsing, ... beruecksichtigen!
     */
    public static void aufgabeZwei(@NotNull List<List<Node> > adj) {
        // TODO ADD CODE HERE
        Scanner command = new Scanner(System.in);
        System.out.println("Enter command: ");
        boolean running = true;

        while(running){
            String[] nextLine = command.nextLine().split(" ");
            if(nextLine.length >=1) {
                String cmd = nextLine[0];
                int[] parameter;

                try{
                    String[] tmp = Arrays.copyOfRange(nextLine,1,nextLine.length);
                    parameter = Arrays.stream(tmp).mapToInt(Integer::parseInt).toArray();
                } catch (NumberFormatException ex) {
                    System.err.println("Parsen nicht möglich kein Int? "+ex.getMessage());
                    break;
                }

                switch(cmd){
                    case "add": // "Ursprungsknoten Zielknoten Kosten"
                        if(parameter.length == 3) {
                            adj.get(parameter[0]).add(new Node(parameter[1], parameter[2]));
                            System.out.println("Knoten hinzugefügt!");
                        } else {
                            System.err.println("Falsche Parameteranzahl!");
                        }
                        break;

                    case "print":
                        System.out.println("Grafik wird ausgegeben!");
                        adj.forEach(System.out::println);
                        break;
                    case "remove": // "Ursprungsknoten Zielknoten"
                        if(parameter.length == 2) {
                            adj.get(parameter[0]).removeIf(x -> x.node == parameter[1]);
                            System.out.println("Kante entfernt!");
                        } else {
                            System.err.println("Falsche Parameteranzahl!");
                        }
                        break;
                    case "exit":
                        System.out.println("Programm geschlossen.");
                        running = false;
                        break;
                    default:
                        System.out.println("Kommando nicht erkannt!");
                        break;
                }
            } else {
                System.err.println("Falsche Parameteranzahl!");
            }
        }
        command.close();
    }
}

// Knotenimplementierung
class Node implements Comparator<Node> {
    public int node;
    public int cost;

    public Node()
    {
    }

    public Node(int node, int cost)
    {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compare(Node node1, Node node2)
    {
        if (node1.cost < node2.cost)
            return -1;
        if (node1.cost > node2.cost)
            return 1;
        return 0;
    }

    @Override
    public String toString() {
        return "Node{" +
                "node=" + node +
                ", cost=" + cost +
                '}';
    }
}
