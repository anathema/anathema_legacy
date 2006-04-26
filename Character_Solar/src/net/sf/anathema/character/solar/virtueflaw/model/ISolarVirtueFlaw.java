package net.sf.anathema.character.solar.virtueflaw.model;

import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface ISolarVirtueFlaw extends IVirtueFlaw {

  public ITextualDescription getDescription();

  public ITextualDescription getLimitBreak();
}