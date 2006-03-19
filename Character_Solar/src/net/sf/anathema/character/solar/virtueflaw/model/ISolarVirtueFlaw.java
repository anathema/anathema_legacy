package net.sf.anathema.character.solar.virtueflaw.model;

import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.lib.workflow.textualdescription.ISimpleTextualDescription;

public interface ISolarVirtueFlaw extends IVirtueFlaw {

  public ISimpleTextualDescription getDescription();

  public ISimpleTextualDescription getLimitBreak();
}