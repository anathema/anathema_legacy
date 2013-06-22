package net.sf.anathema.character.library.virtueflaw.model;

import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class DescriptiveVirtueFlawImpl extends VirtueFlawImpl implements DescriptiveVirtueFlaw {

  private final ITextualDescription description = new SimpleTextualDescription("");
  private final ITextualDescription limitBreak = new SimpleTextualDescription("");

  public DescriptiveVirtueFlawImpl(Hero hero) {
    super(hero);
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