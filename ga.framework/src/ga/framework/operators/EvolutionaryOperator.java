package ga.framework.operators;

import ga.framework.model.Problem;
import ga.framework.model.Solution;

public interface EvolutionaryOperator {
	public Solution evolve(Solution solution, Problem problem)throws EvolutionException;

}
