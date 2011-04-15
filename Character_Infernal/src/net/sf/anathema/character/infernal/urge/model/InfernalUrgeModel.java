package net.sf.anathema.character.infernal.urge.model;

import net.sf.anathema.character.generic.additionaltemplate.AbstractAdditionalModelAdapter;
import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.infernal.urge.InfernalUrgeTemplate;
import net.sf.anathema.lib.control.change.IChangeListener;

public class InfernalUrgeModel extends AbstractAdditionalModelAdapter implements IInfernalUrgeModel {

  private InfernalUrgeTemplate template;
  private String urge = "";

  public InfernalUrgeModel(InfernalUrgeTemplate template)
  {
	  this.template = template;
  }
  
  @Override
  public AdditionalModelType getAdditionalModelType() {
  	return AdditionalModelType.Concept;
  }

  @Override
  public String getTemplateId()
  {
  	return template.getId();
  }
  
  @Override
  public void addChangeListener(IChangeListener listener)
  {
  }

  @Override
  public String getUrge() {
    return urge;
  }
  
  @Override
  public void setUrge(String urge)
  {
	  this.urge = urge;
  }
}