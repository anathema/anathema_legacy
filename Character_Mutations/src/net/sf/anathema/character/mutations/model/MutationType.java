package net.sf.anathema.character.mutations.model;

import net.sf.anathema.character.library.quality.presenter.IQualityType;

public enum MutationType implements IQualityType
{
	  Pox, Affliction, Blight, Abomination;

	  public String getId() {
	    return name();
	  }
}
