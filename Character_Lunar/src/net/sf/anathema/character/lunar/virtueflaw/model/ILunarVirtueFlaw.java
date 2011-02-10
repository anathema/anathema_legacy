package net.sf.anathema.character.lunar.virtueflaw.model;

import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface ILunarVirtueFlaw extends IVirtueFlaw {

  public ITextualDescription getDescription();

  public ITextualDescription getLimitBreak();
}