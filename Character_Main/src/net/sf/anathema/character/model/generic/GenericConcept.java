package net.sf.anathema.character.model.generic;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IConcept;
import net.sf.anathema.character.model.concept.ICharacterConcept;
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

  public String getNatureName() {
    INatureType natureType = getNatureType();
    return natureType == null ? null : natureType.getName();
  }

  private INatureType getNatureType() {
    final INatureType[] natureType = new INatureType[1];
    characterConcept.getWillpowerRegainingConcept().accept(new IWillpowerRegainingConceptVisitor() {
      public void accept(INature nature) {
        natureType[0] = nature.getDescription().getType();
      }
    });
    INatureType type = natureType[0];
    return type;
  }

  public String getWillpowerCondition() {
    INatureType natureType = getNatureType();
    return natureType == null ? null : natureType.getWillpowerCondition();
  }

  public ICasteType getCasteType() {
    return characterConcept.getCaste().getType();
  }
}