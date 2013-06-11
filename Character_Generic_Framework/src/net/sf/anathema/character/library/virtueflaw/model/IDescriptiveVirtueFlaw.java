package net.sf.anathema.character.library.virtueflaw.model;

import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface IDescriptiveVirtueFlaw extends IVirtueFlaw {

  ITextualDescription getDescription();

  ITextualDescription getLimitBreak();
}