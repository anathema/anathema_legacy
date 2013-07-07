package net.sf.anathema.herotype.solar.model.curse;

import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface DescriptiveVirtueFlaw extends VirtueFlaw {

  ITextualDescription getDescription();

  ITextualDescription getLimitBreak();
}