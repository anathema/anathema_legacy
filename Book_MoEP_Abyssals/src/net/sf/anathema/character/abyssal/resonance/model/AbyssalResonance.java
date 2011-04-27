package net.sf.anathema.character.abyssal.resonance.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.library.virtueflaw.model.VirtueFlaw;

public class AbyssalResonance extends VirtueFlaw {

  public AbyssalResonance(ICharacterModelContext context) {
	  super(context);
	  this.getName().setText("Flawed Virtue");
  }
  
  protected String getLimitString()
  {
	  return "VirtueFlaw.Resonance";
  }
}