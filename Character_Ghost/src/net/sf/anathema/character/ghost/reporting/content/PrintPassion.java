package net.sf.anathema.character.ghost.reporting.content;

import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.character.reporting.pdf.content.general.NamedValue;
import net.sf.anathema.lib.resources.IResources;

public class PrintPassion implements NamedValue {
  private IResources resources;
  private final VirtueType virtue;
  private final ISubTrait passion;

  public PrintPassion(IResources resources, VirtueType virtue, ISubTrait passion) {
    this.resources = resources;
    this.virtue = virtue;
    this.passion = passion;
  }

  @Override
  public String getLabel() {
    String virtueString = resources.getString(virtue.getId());
    return "(" + virtueString + ") " + passion.getName();
  }

  @Override
  public int getValue() {
    return passion.getCurrentValue();
  }
}
