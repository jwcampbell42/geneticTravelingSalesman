public class Edge
{
  private double weight;
  private Vertex vertex1;
  private Vertex vertex2;
  public Edge(Vertex vtx1, Vertex vtx2, double weight)
  {
    this.weight = weight;
	vertex1 = vtx1;
	vertex2 = vtx2;
  }
  public double getWeight()
  {
    return weight;
  }
  public Vertex getVertex1()
  {
    return vertex1; 
  }
  public Vertex getVertex2()
  {
    return vertex2;
  }
  public String toString()
  {
    String str = "";
	str += "\n(" + vertex1.getName() + ",  " + vertex2.getName() + ",  " + weight + ")";
	return str;
  }
}