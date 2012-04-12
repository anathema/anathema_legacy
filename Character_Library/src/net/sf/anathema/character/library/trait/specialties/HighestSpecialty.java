package net.sf.anathema.character.library.trait.specialties;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public class HighestSpecialty {
    
    private int value = 0;
    private String name;
    
    public HighestSpecialty( IGenericCharacter character, AbilityType type ) {
        for(INamedGenericTrait t : character.getSpecialties( type )) {
            if( value < t.getCurrentValue() ) {
                value = t.getCurrentValue();
                name  = t.getName();
            }
        }
    }
    
    public int getValue() {
        return value;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
}