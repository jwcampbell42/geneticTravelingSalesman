import java.util.*;
import java.util.ArrayList;
import java.io.*;
public class TravelingSalesman
{
	static Random random = new Random();
	public static void main(String[] args) throws java.io.FileNotFoundException
	{
		Graph graph = new Graph();
		
		String vertFile = args[0];
		String edgeFile = args[1];
		String populationSizeString = args[2];
		String numberGenerationsString = args[3];
		int populationSize = Integer.parseInt(populationSizeString);
		int numberGeneration = Integer.parseInt(numberGenerationsString);
		if(fileExists(vertFile) &&fileExists(edgeFile))
		{
			ArrayList<String> names = readVertices(vertFile);
			int count = 0;
			for(String name: names){
				graph.addVertex(name, count);
				count++;
			}
			readEdges(edgeFile, graph);
			ArrayList<Chromosome> population = new ArrayList<>();
			ArrayList<Vertex> vertices = graph.getVertices();
			for(int i = 0; i < populationSize; i++)
			{
				Chromosome chromo = new Chromosome(vertices);
				population.add(chromo);
			}
			ArrayList<Chromosome>sorted = sort(population);
			
			System.out.println("\n");
			ArrayList<Chromosome> generation = generations(sorted, numberGeneration);
			Chromosome best = null;
			double bestWeight = -999999999;
			for(Chromosome c: generation)
			{
				double cWeight = c.getWeight();
				if(cWeight > bestWeight)
				{
					bestWeight = cWeight;
					best = c;
				}
			}
			System.out.println(best.toString());
			System.out.println("\nBest Distance: " + best.getWeight()*-1);

			
			
		}
	}
	public static ArrayList<Chromosome> generations(ArrayList<Chromosome> population, int numberGeneration)
	{
		if(numberGeneration == 0)
			return population;
		ArrayList<Chromosome> nextGen = new ArrayList<>();
		int popSize = population.size();
		nextGen.addAll(population.subList(popSize-10, popSize));

		for(int i = 0; i <10; i++)
		{
			population.remove(population.size()-1);
		}
		while(nextGen.size() < population.size()+10)
		{
			selectForCrossover(population, nextGen);
		}
		numberGeneration--;
		return generations(nextGen, numberGeneration);
		
	}
	public static ArrayList<Chromosome> selectForCrossover(ArrayList<Chromosome> population, ArrayList<Chromosome> nextGen)
	{
		
		ArrayList<Chromosome> selected = new ArrayList<>();
		for(int i = 0; i < 10; i++)
		{
			selected.add(population.get(random.nextInt(population.size())));
		}
		ArrayList<Chromosome> sortedSelected = new ArrayList<>();
		sortedSelected.addAll(sort(selected));
		selected.clear();
		for(int j = 1; j <= 4; j++)
		{
			selected.add(sortedSelected.get(sortedSelected.size()-j));
		}
		Chromosome parent1 = selected.remove(random.nextInt(selected.size()));
		Chromosome parent2 = selected.remove(random.nextInt(selected.size()));
		Chromosome child = new Chromosome(parent1, parent2);
		nextGen.add(child);
		return nextGen;
		
		
	}
	
	public static ArrayList<Chromosome> sort(ArrayList<Chromosome> population)
	{
		if(population.size() <= 1) return population;
		
		int pivotLocation = random.nextInt(population.size());
		Chromosome pivot = population.get(pivotLocation);
		population.remove(pivot);
		ArrayList<Chromosome> left = new ArrayList<>();
		ArrayList<Chromosome> right = new ArrayList<>();
		for(Chromosome chromo: population)
		{
			if(chromo.getWeight() <= pivot.getWeight())
				left.add(chromo);
			else
				right.add(chromo);
		}
		sort(left);
		sort(right);
		
		population.clear();
		population.addAll(left);
		population.add(pivot);
		population.addAll(right);
		return population;
	}
	public static ArrayList<String> readVertices(String fileName)throws java.io.FileNotFoundException
	  {
	     Scanner input = new Scanner (new File(fileName));
	     ArrayList<String> names = new ArrayList<String>();
		 while (input.hasNext())
	      {
	         String name = input.next();
			 if(name != null )
			   names.add(name);
		  }
		 input.close();
		  return names;
	  }
	  public  static void readEdges(String filename, Graph graph)throws java.io.FileNotFoundException
	  {
	    Scanner input = new Scanner (new File(filename));
		double weight =0;
		String vertex1 ="";
		String vertex2 = "";
		while(input.hasNext())
		{
		  vertex1 = input.next();
		  vertex2 = input.next();
		  weight = input.nextDouble();
		  graph.addEdge(vertex1, vertex2, weight);
		}
		input.close();
	  }
	  private static boolean fileExists(String toRead)
	   {
	      
		  try
		  {
		    FileIO file = new FileIO(toRead, FileIO.FOR_READING);
			file.close();
			return true;
	      }
		  catch(FileIOException fioe)
		  {
		    return false;
		  }
		 
	   }
}