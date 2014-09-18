import java.util.ArrayList;
public class Vertex
{
  private ArrayList<Edge> edges;
  private String name;
  private boolean visited = false;
  private Vertex parent;
  private int number;
  public Vertex(String name)
  {
    edges = new ArrayList<Edge>();
	this.name = name;
  }
  public void setNumber(int num)
  {
	  number = num;
  }
  public int getNumber()
  {
	  return number;
  }
  public void setVisitedTrue()
  {
    visited = true;
  }
  public void setVisitedFalse()
  {
    visited = false;
  }
  public boolean visitedStatus()
  {
    return visited;
  }
  public void addEdge(Edge edge)
  {
    edges.add(edge);
  }
  public String getName()
  {
    return name;
  }
  public void setParent(Vertex parent)
  {
    this.parent = parent;
  }
  public Vertex getParent()
  {
    return parent;
  }
  public ArrayList<Edge> getEdges()
  {
    return edges;
  }
  public String stringEdges()
  {
    String str = "";
	
	for(Edge e: edges)
	{
	  str+= '\n' + e.toString();  
    } 
	return str;
  }	
  
 } 