package net.sf.anathema.herotype.solar.model;

import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface DescriptiveVirtueFlaw extends VirtueFlaw {

  ITextualDescription getDescription();

  ITextualDescription getLimitBreak();
}