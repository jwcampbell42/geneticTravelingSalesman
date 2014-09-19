import java.util.*;
import java.util.ArrayList;

public class Chromosome {
	private ArrayList<Vertex> genes = new ArrayList<>();
	private double weight; 
	private Vertex start;
	Random random = new Random();
	private ArrayList<Edge> edges = new ArrayList<>();
	public Chromosome(ArrayList<Vertex> vertices)
	{
		start = vertices.get(0);
		for(Vertex v: vertices) genes.add(v);
		genes.remove(0);
		Collections.shuffle(genes);
		calculateWeight();
	}
	public Chromosome(Chromosome parent1, Chromosome parent2)
	{
		start = parent1.getStart();
		int crossoverPoint = random.nextInt(parent1.size());
		
		genes.addAll(parent1.getGenes().subList(0, crossoverPoint));
		genes.addAll((parent2.getGenes().subList(crossoverPoint, parent2.size())));
		removeRevisits(parent1.getGenes());
		calculateWeight();
	}
	
	private void removeRevisits(ArrayList<Vertex> vertices)
	{
		ArrayList<Vertex> missing = new ArrayList<>();
		int count = 0;
		for(Vertex v: vertices)
		{
			
				if(!genes.contains(v))
				{
					missing.add(v);
				}
				
		}
		for(Vertex g: genes)
		{
			if(missing.size()==0) break;
			if(!g.visitedStatus())
			{
				g.setVisitedTrue();				
			}
			else
			{
				genes.set(count, missing.remove(0));
			}
			
			count++;
		}
		for(Vertex g: genes)
			g.setVisitedFalse();
		
		
	}
	private ArrayList<Vertex> getGenes()
	{
		return genes;
	}
	private int size()
	{
		return genes.size();
	}
	public Vertex getStart()
	{
		return start;
	}
	public void calculateWeight()
	{
		weight = 0;
		Vertex prev = start;
		for(Vertex v: genes)
		{
			for(Edge e: v.getEdges())
			{
				if(e.getVertex1() == prev || e.getVertex2() == prev)
				{
					weight -= e.getWeight();
					edges.add(e);
					prev = v;
					break;
				}
			}
		}
		for(Edge e: prev.getEdges())
		{
			if(e.getVertex1() == start || e.getVertex2() == start)
			{
				weight -= e.getWeight();
				edges.add(e);
				break;
			}
		}
		

	
	}
	public ArrayList<Vertex> getFullChromosome()
	{
		ArrayList<Vertex> full = new ArrayList<>();
		full.add(start);
		full.addAll(genes);
		full.add(start);
		return full;
	}
	public double getWeight()
	{
		
		return weight;
	}
	public String toString()
	{
		String str = start.getNumber() + ":  " + start.getName();
		for(Vertex v: genes)
		{
			str += "->\n" + +v.getNumber() + ":  " + v.getName();
		}
		str += "->\n" + start.getNumber() + ":  " + start.getName();
		return str;
	}
}
