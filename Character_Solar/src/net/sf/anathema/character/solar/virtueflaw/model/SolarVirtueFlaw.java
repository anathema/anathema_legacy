package net.sf.anathema.character.solar.virtueflaw.model;

import net.sf.anathema.character.library.virtueflaw.model.VirtueFlaw;
import net.sf.anathema.lib.workflow.textualdescription.ISimpleTextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class SolarVirtueFlaw extends VirtueFlaw implements ISolarVirtueFlaw {

  private final ISimpleTextualDescription description = new SimpleTextualDescription(""); //$NON-NLS-1$
  private final ISimpleTextualDescription limitBreak = new SimpleTextualDescription(""); //$NON-NLS-1$

  public ISimpleTextualDescription getDescription() {
    return description;
  }

  public ISimpleTextualDescription getLimitBreak() {
    return limitBreak;
  }

  @Override
  public boolean isFlawComplete() {
    return super.isFlawComplete() && !(limitBreak.isEmpty() || description.isEmpty());
  }
}