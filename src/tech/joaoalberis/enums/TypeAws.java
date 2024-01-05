package tech.joaoalberis.enums;

public enum TypeAws {
    axe("armourers:axe"),
    bow("armourers:bow"),
    chest("armourers:chest"),
    feet("armourers:feet"),
    head("armourers:head"),
    hoe("armourers:hoe"),
    legs("armourers:legs"),
    outfit("armourers:outfit"),
    pickaxe("armourers:pickaxe"),
    shovel("armourers:shovel"),
    sword("armourers:sword"),
    wings("armourers:wings");

    private String name;

    TypeAws(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }


}
