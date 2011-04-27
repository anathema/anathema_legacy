package net.sf.anathema.character.sidereal.paradox.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.library.virtueflaw.model.VirtueFlaw;

public class SiderealParadox extends VirtueFlaw {

  public SiderealParadox(ICharacterModelContext context) {
	  super(context);
	  this.getName().setText("Flawed Virtue");
  }
  
  protected String getLimitString()
  {
	  return "VirtueFlaw.Paradox";
  }
}