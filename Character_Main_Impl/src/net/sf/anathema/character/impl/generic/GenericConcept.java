package net.sf.anathema.character.impl.generic;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IConcept;
import net.sf.anathema.character.model.concept.ICharacterConcept;
import net.sf.anathema.character.model.concept.IMotivation;
import net.sf.anathema.character.model.concept.IWillpowerRegainingConceptVisitor;
import net.sf.anathema.lib.resources.IResources;

public class GenericConcept implements IConcept {

  private final ICharacterConcept characterConcept;

  public GenericConcept(ICharacterConcept characterConcept) {
    this.characterConcept = characterConcept;
  }

  @Override
  public String getWillpowerRegainingConceptName() {
    final String[] conceptName = new String[1];
    characterConcept.getWillpowerRegainingConcept().accept(new IWillpowerRegainingConceptVisitor() {
      @Override
      public void accept(IMotivation motivation) {
        conceptName[0] = motivation.getDescription().getText();
      }
    });
    return conceptName[0];
  }

  @Override
  public String getWillpowerRegainingComment(final IResources resources) {
    final String[] conceptName = new String[1];
    characterConcept.getWillpowerRegainingConcept().accept(new IWillpowerRegainingConceptVisitor() {
      @Override
      public void accept(IMotivation motivation) {
        conceptName[0] = motivation.getDescription().getText();
      }
    });
    return conceptName[0];
  }

  @Override
  public ICasteType getCasteType() {
    return characterConcept.getCaste().getType();
  }
  
  @Override
  public int getAge() {
    return characterConcept.getAge().getValue();
  }
}