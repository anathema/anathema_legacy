package net.sf.anathema.character.library.virtueflaw.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class DescriptiveVirtueFlaw extends VirtueFlaw implements IDescriptiveVirtueFlaw {

  private final ITextualDescription description = new SimpleTextualDescription(""); //$NON-NLS-1$
  private final ITextualDescription limitBreak = new SimpleTextualDescription(""); //$NON-NLS-1$

  public DescriptiveVirtueFlaw(ICharacterModelContext context) {
    super(context);
  }

  @Override
  public ITextualDescription getDescription() {
    return description;
  }

  @Override
  public ITextualDescription getLimitBreak() {
    return limitBreak;
  }

  @Override
  public boolean isFlawComplete() {
    return super.isFlawComplete() && !(limitBreak.isEmpty() || description.isEmpty());
  }
}