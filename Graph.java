import java.util.ArrayList;

public class Graph 
{
  private ArrayList<Edge> edges;
  private ArrayList<Vertex> vertices;
  
  
  public Graph()
  {
    
	vertices = new ArrayList<Vertex>();
	edges = new ArrayList<Edge>();
  }
  public void addVertex(String name, int number)
  {
    Vertex vert = new Vertex(name);
    vert.setNumber(number);
    vertices.add(vert);
    
  }
  public ArrayList<Vertex> getVertices()
  {
    return vertices;
  }
  public ArrayList<Edge> getEdges()
  {
    return edges;
  }
  public void addEdge(String name1, String name2, double edge_weight)
  {
	 for(Vertex v1: vertices)
	 {
	   if(v1.getName().equals(name1))
	   {
	     for(Vertex v2: vertices)
	     {
	      if(v2.getName().equals(name2))
	      {
			Edge edge = new Edge(v1, v2, edge_weight);
			v1.addEdge(edge);
			edges.add(edge);
			break;
		  }
		 }
		 break;
		}
	 }
	
  }
  public  void printVertices()
  {
    for(Vertex v: vertices)
	{
	  System.out.println('\n' + v.getName());
	  System.out.println(v.stringEdges());
	}
  }
  public  void printEdges()
  {
    for(Edge e: edges)
	{
	  System.out.println(e.toString());
	}
  }
}