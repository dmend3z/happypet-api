package com.happypet.HappyPet.enumerators;

public enum PetAge {
    BABY("BABY"),
    YOUNG("YOUNG"),
    ADULT("ADULT"),
    SENIOR("SENIOR");


    private String name;

    PetAge(String name) {
        this.name = name;
    }

    /**
     * Get age name
     * @return the name
     */
    public String getName() {
        return name;
    }
}
