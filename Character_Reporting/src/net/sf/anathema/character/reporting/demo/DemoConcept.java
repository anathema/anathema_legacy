package net.sf.anathema.character.reporting.demo;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IConcept;

public class DemoConcept implements IConcept {

  private ICasteType casteType = ICasteType.NULL_CASTE_TYPE;
  private String conceptText;
  private String willpowerCondition;
  private String willpowerRegainingConceptName;

  public ICasteType getCasteType() {
    return casteType;
  }

  public String getConceptText() {
    return conceptText;
  }

  public String getWillpowerCondition() {
    return willpowerCondition;
  }

  public String getWillpowerRegainingConceptName() {
    return willpowerRegainingConceptName;
  }

  public void setCasteType(ICasteType casteType) {
    this.casteType = casteType;
  }

  public void setConceptText(String conceptText) {
    this.conceptText = conceptText;
  }

  public void setWillpowerCondition(String willpowerCondition) {
    this.willpowerCondition = willpowerCondition;
  }

  public void setWillpowerRegainingConceptName(String willpowerRegainingConceptName) {
    this.willpowerRegainingConceptName = willpowerRegainingConceptName;
  }
}