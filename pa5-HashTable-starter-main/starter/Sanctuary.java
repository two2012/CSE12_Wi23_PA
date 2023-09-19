/*
 * Name: Wen Guo
 * Email: w5guo@ucsd.edu
 * PID: A17630856
 * Sources Used: JDK 17 Doc
 * this is Sanctuary class to keep track of how many of 
 * each species are currently located on its grounds.
 */

import java.util.HashMap;

/**
 * this is Sanctuary class to keep track of how many of
 * each species are currently located on its grounds.
 */
public class Sanctuary {

    // instance variables
    HashMap<String, Integer> sanctuary;
    private final int maxAnimals;
    private final int maxSpecies;

    // avoid magic numbers
    private static final int ZERO = 0;

    /**
     * Initialize the HashMap with no elements.
     * Initialize the other instance variables accordingly.
     * 
     * @param maxAnimals maximum number of animals
     * @param maxSpecies maximum number of species
     */
    public Sanctuary(int maxAnimals, int maxSpecies) {

        // check if maxAnimals and maxSpecies less than 0
        if (maxAnimals <= ZERO ||
                maxSpecies <= ZERO ||
                maxSpecies > maxAnimals) {

            throw new IllegalArgumentException();
        } else {
            this.maxAnimals = maxAnimals;
            this.maxSpecies = maxSpecies;
            this.sanctuary = new HashMap<>(maxSpecies);
        }
    }

    /**
     * Return the number of animals in the sanctuary
     * that are of the given species. If the given
     * species does not exist in the sanctuary, return 0.
     * 
     * @param species given species
     * @return number of animals
     */
    public int countForSpecies(String species) {
        // check if specise is null
        if (species == null) {
            throw new IllegalArgumentException();
        } else {
            // check if specise is in sanctuary
            if (this.sanctuary.containsKey(species)) {
                return this.sanctuary.get(species);
            } else {
                return ZERO;
            }
        }

    }

    /**
     * Return the total number of animals in the sanctuary.
     * 
     * @return total number of animals
     */
    public int getTotalAnimals() {
        int totalAnimals = ZERO;
        // loop through sanctuary find each number of specie
        for (String specie : this.sanctuary.keySet()) {
            totalAnimals += this.sanctuary.get(specie);
        }

        return totalAnimals;

    }

    /**
     * Return the total number of species in the sanctuary.
     * 
     * @return number of species
     */
    public int getTotalSpecies() {
        return this.sanctuary.size();
    }

    /**
     * Return the maximum allowed number of animals in the sanctuary.
     * 
     * @return maximum allowed number of animals
     */
    public int getMaxAnimals() {
        return maxAnimals;
    }

    /**
     * Return the maximum allowed number of species in the sanctuary.
     * 
     * @return maximum allowed number of species
     */
    public int getMaxSpecies() {
        return maxSpecies;
    }

    /**
     * If it does not exceed the maxAnimals nor maxSpecies of the sanctuary,
     * add num animals of species to the sanctuary. If adding num animals
     * exceeds the maximum limit, add as many animals as permitted.
     * Return the number of animals that could not be rescued.
     * 
     * @param species rescue species
     * @param num     number of animals
     * @return number of animals that could not be rescued.
     */
    public int rescue(String species, int num) {

        // check if num are illegal
        if (species == null || num <= ZERO) {
            throw new IllegalArgumentException();
        }

        int currentNumAnimals = getTotalAnimals() + num;
        int notAddedAnimals = currentNumAnimals - maxAnimals;

        // check if the species is a new one
        if (sanctuary.containsKey(species)) {
            int existAnimals = sanctuary.get(species);
            if (notAddedAnimals <= 0) {
                sanctuary.put(species, num + existAnimals);
                return ZERO;
            } else {
                if (notAddedAnimals == num){
                    return num;
                }
                sanctuary.put(species, num - notAddedAnimals + existAnimals);
                return notAddedAnimals;
            }

            // the species is a new one
        } else {
            // check if species is full
            if (getTotalSpecies() == maxSpecies) {
                return num;
                // species is not full
            } else {
                if (notAddedAnimals <= 0) {
                    sanctuary.put(species, num);
                    return ZERO;
                } else {
                    if (num == notAddedAnimals){
                        return num;
                    }
                    sanctuary.put(species, num - notAddedAnimals);
                    return notAddedAnimals;

                }
            }

        }

    }

    /**
     * Remove num animals of species from the sanctuary.
     * Remove the species from the sanctuary if its remaining
     * count reaches 0.
     * 
     * @param species species from the sanctuary
     * @param num     num animals
     */
    public void release(String species, int num) {
        // check if arguments is illegal
        if (species == null || !sanctuary.containsKey(species)) {
            throw new IllegalArgumentException();
        }
        // get number of animal in species
        int value = sanctuary.get(species);
        // check if num is illegal
        if (num <= 0 || num > value) {
            throw new IllegalArgumentException();
        }

        if (value == num) {
            sanctuary.remove(species);
        } else {
            sanctuary.put(species, value - num);
        } 
    }
}
