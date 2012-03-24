package net.sf.anathema.character.impl.generic;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IConcept;
import net.sf.anathema.character.model.concept.ICharacterConcept;
import net.sf.anathema.character.model.concept.IMotivation;
import net.sf.anathema.lib.resources.IResources;

public class GenericConcept implements IConcept {

  private final ICharacterConcept characterConcept;

  public GenericConcept(ICharacterConcept characterConcept) {
    this.characterConcept = characterConcept;
  }

  @Override
  public String getWillpowerRegainingConceptName() {
    IMotivation motivation = characterConcept.getWillpowerRegainingConcept();
    return motivation.getDescription().getText();
  }

  @Override
  public String getWillpowerRegainingComment(final IResources resources) {
    IMotivation motivation = characterConcept.getWillpowerRegainingConcept();
    return motivation.getDescription().getText();
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