package ga.framework;

import ga.framework.model.NoSolutionException;
import ga.framework.model.Problem;
import ga.framework.model.Solution;
import ga.framework.operators.*;

import java.util.*;
import java.util.stream.Collectors;

public class GeneticAlgorithm {
    private Problem problem;
    private Integer size;
    private List<EvolutionaryOperator> operators;
    private FitnessEvaluator fitnessEvaluator;
    private SurvivalOperator survivalOperator;
    private SelectionOperator selectionOperator;
    private Integer counter;

    public SizeBuilder solve(Problem problem){
        SizeBuilder sizeBuilder = new SizeBuilder();
        sizeBuilder.problem = problem;
        return sizeBuilder;
    }

    public class SizeBuilder{
        Problem problem;
        public EOperatorsBuilder withPopulationOfSize(int i){
            EOperatorsBuilder eOperatorsBuilder = new EOperatorsBuilder();
            eOperatorsBuilder.problem = problem;
            eOperatorsBuilder.size = i;
            return eOperatorsBuilder;
        }
    }
    public class EOperatorsBuilder{
        Problem problem;
        int size;
        public FitnessEvaluatorBuilder evolvingSolutionsWith(EvolutionaryOperator evolutionaryOperator){
            FitnessEvaluatorBuilder fitnessEvaluatorBuilder = new FitnessEvaluatorBuilder();
            fitnessEvaluatorBuilder.list.add(evolutionaryOperator);
            fitnessEvaluatorBuilder.problem = problem;
            fitnessEvaluatorBuilder.size = size;
            return fitnessEvaluatorBuilder;
        }
    }
    public class FitnessEvaluatorBuilder{
        Problem problem;
        int size;
        List<EvolutionaryOperator> list = new ArrayList<>();
        public SelectionOperatorBuilder evaluatingSolutionsBy(FitnessEvaluator fitnessEvaluator){
            SelectionOperatorBuilder selectionOperatorBuilder = new SelectionOperatorBuilder();
            selectionOperatorBuilder.fitnessEvaluator = fitnessEvaluator;
            selectionOperatorBuilder.problem = problem;
            selectionOperatorBuilder.size = size;
            selectionOperatorBuilder.list = list;
            return selectionOperatorBuilder;
        }
        public FitnessEvaluatorBuilder evolvingSolutionsWith(EvolutionaryOperator evolutionaryOperator){
            FitnessEvaluatorBuilder fitnessEvaluatorBuilder = new FitnessEvaluatorBuilder();
            fitnessEvaluatorBuilder.list = list;
            fitnessEvaluatorBuilder.list.add(evolutionaryOperator);
            fitnessEvaluatorBuilder.problem = problem;
            fitnessEvaluatorBuilder.size = size;
            return fitnessEvaluatorBuilder;
        }
    }
    public class SelectionOperatorBuilder{
        Problem problem;
        int size;
        List<EvolutionaryOperator> list;
        FitnessEvaluator fitnessEvaluator;
        SurvivalOperator survivalOperator;
        public SelectionOperatorBuilder survivalOperatorIs(SurvivalOperator survivalOperator){
            this.survivalOperator = survivalOperator;
            return this;
        }
        public CounterBuilder performingSelectionWith(SelectionOperator selectionOperator){
            CounterBuilder counterBuilder = new CounterBuilder();
            counterBuilder.selectionOperator = selectionOperator;
            counterBuilder.problem = problem;
            counterBuilder.size = size;
            counterBuilder.list = list;
            counterBuilder.fitnessEvaluator = fitnessEvaluator;
            return counterBuilder;
        }
    }
    public class CounterBuilder{
        Problem problem;
        int size;
        List<EvolutionaryOperator> list;
        FitnessEvaluator fitnessEvaluator;
        SelectionOperator selectionOperator;
        public GeneticAlgorithm stoppingAtEvolution(int counter){
            GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
            geneticAlgorithm.problem = problem;
            geneticAlgorithm.size = size;
            geneticAlgorithm.operators = list;
            geneticAlgorithm.fitnessEvaluator = fitnessEvaluator;
            geneticAlgorithm.selectionOperator = selectionOperator;
            geneticAlgorithm.counter = counter;
            return geneticAlgorithm;
        }
    }

    public List<Solution> runOptimization(){
        try {
            // creating start population:
            List<Solution> currPopulation = new ArrayList<>();
            for (int i = 0; i<size; i++){
                currPopulation.add(this.problem.createNewSolution());
            }
            // fitness of start population
            fitnessEvaluator.evaluate(currPopulation);
            Random rand = new Random();
            List<Solution> candidates = new ArrayList<>();
            // iterations
            for (int c = 0; c<counter; c++){
                System.out.println("New Population Level");
                while (candidates.size()<size){
                    EvolutionaryOperator operator = operators.get(rand.nextInt(operators.size()));
                    candidates.add(operator.evolve(selectionOperator.selectParent(currPopulation)));
                }
                // fitness of candidates
                fitnessEvaluator.evaluate(candidates);
                // candidates to current population
                currPopulation.addAll(candidates);
                // use of survival operator:
                currPopulation =  survivalOperator.selectPopulation(currPopulation, size);
            }
            return currPopulation;
        }
        catch (NoSolutionException noSolutionException){
            System.out.println("Keine Lösung!");
        }
        catch (EvolutionException evolutionException){
            System.out.println("Das Operator kann nicht angewandt werden!");
        }
        catch (SurvivalException survivalException){
            System.out.println("Keine der Lösungen passt!");
        }
        throw new IllegalStateException();
    }


    public static SelectionOperator simpleSelectionOperator = new SelectionOperator() {
        @Override
        public Solution selectParent(List<Solution> candidates) {
            Optional<Solution> parent = candidates.stream().max(Comparator.comparing(Solution::getFitness));
            if (parent.isEmpty()){
                throw new IllegalStateException();
            }
            return parent.get();
        }
    };

}
