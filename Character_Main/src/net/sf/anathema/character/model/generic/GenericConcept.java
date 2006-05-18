package net.sf.anathema.character.model.generic;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IConcept;
import net.sf.anathema.character.model.concept.ICharacterConcept;
import net.sf.anathema.character.model.concept.IMotivation;
import net.sf.anathema.character.model.concept.INature;
import net.sf.anathema.character.model.concept.INatureType;
import net.sf.anathema.character.model.concept.IWillpowerRegainingConceptVisitor;

public class GenericConcept implements IConcept {

  private final ICharacterConcept characterConcept;

  public GenericConcept(ICharacterConcept characterConcept) {
    this.characterConcept = characterConcept;
  }

  public String getConceptText() {
    return characterConcept.getConcept().getText();
  }

  public String getWillpowerRegainingConceptName() {
    final String[] conceptName = new String[1];
    characterConcept.getWillpowerRegainingConcept().accept(new IWillpowerRegainingConceptVisitor() {
      public void accept(INature nature) {
        INatureType natureType = nature.getDescription().getType();
        conceptName[0] = natureType == null ? null : natureType.getId();
      }

      public void accept(IMotivation motivation) {
        conceptName[0] = motivation.getDescription().getText();
      }
    });
    return conceptName[0];
  }

  public ICasteType getCasteType() {
    return characterConcept.getCaste().getType();
  }
}