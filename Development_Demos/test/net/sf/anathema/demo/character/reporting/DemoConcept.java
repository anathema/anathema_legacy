package net.sf.anathema.demo.character.reporting;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IConcept;
import net.sf.anathema.lib.resources.IResources;

public class DemoConcept implements IConcept {

  private int age = 0;
  private ICasteType casteType = ICasteType.NULL_CASTE_TYPE;
  private String conceptText;
  private String willpowerRegainingConceptName;

  @Override
  public int getAge() {
    return age;
  }

  @Override
  public ICasteType getCasteType() {
    return casteType;
  }

  public String getConceptText() {
    return conceptText;
  }

  @Override
  public String getWillpowerRegainingConceptName() {
    return willpowerRegainingConceptName;
  }
  
  public void setAge(int age) {
    this.age = age;
  }

  public void setCasteType(ICasteType casteType) {
    this.casteType = casteType;
  }

  public void setConceptText(String conceptText) {
    this.conceptText = conceptText;
  }

  public void setWillpowerRegainingConceptName(String willpowerRegainingConceptName) {
    this.willpowerRegainingConceptName = willpowerRegainingConceptName;
  }

  @Override
  public String getWillpowerRegainingComment(IResources resources) {
    return getWillpowerRegainingConceptName();
  }
}