package com.happypet.HappyPet.enumerators;

public enum PetSpecie {

    CAT("CAT"),
    DOG("DOG"),
    BIRD("BIRD"),
    FARM("FARM"),
    HORSE("HORSE"),
    FISH("FISH");

    private String name;

    PetSpecie(String name) {
        this.name = name;
    }
    /**
     * Get specie name
     * @return the name
     */
    public String getName() {
        return name;
    }
}
