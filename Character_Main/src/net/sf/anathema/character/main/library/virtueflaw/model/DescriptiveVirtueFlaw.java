package net.sf.anathema.character.main.library.virtueflaw.model;

import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface DescriptiveVirtueFlaw extends VirtueFlaw {

  ITextualDescription getDescription();

  ITextualDescription getLimitBreak();
}