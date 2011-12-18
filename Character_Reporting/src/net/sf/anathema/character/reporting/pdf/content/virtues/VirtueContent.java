package net.sf.anathema.character.reporting.pdf.content.virtues;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.reporting.pdf.content.ISubContent;
import net.sf.anathema.character.reporting.pdf.content.general.NamedValue;
import net.sf.anathema.lib.resources.IResources;

public class VirtueContent implements ISubContent {

  private IGenericCharacter character;
  private IResources resources;

  public VirtueContent(IResources resources, IGenericCharacter character) {
    this.character = character;
    this.resources = resources;
  }

  public NamedValue getUpperLeftVirtue() {
    return getVirtue(VirtueType.Compassion);
  }

  public NamedValue getUpperRightVirtue() {
    return getVirtue(VirtueType.Temperance);
  }

  public NamedValue getLowerLeftVirtue() {
    return getVirtue(VirtueType.Conviction);
  }

  public NamedValue getLowerRightVirtue() {
    return getVirtue(VirtueType.Valor);
  }

  private NamedValue getVirtue(VirtueType type) {
    IGenericTrait trait = getVirtueCollection().getTrait(type);
    String printName = resources.getString(trait.getType().getId());
    return new NamedValue(printName, trait.getCurrentValue());
  }

  private IGenericTraitCollection getVirtueCollection() {
    return character.getTraitCollection();
  }

  public boolean hasContent() {
    return true;
  }

  public String getHeader() {
    return resources.getString("Sheet.Header." + getHeaderKey());
  }

  public String getHeaderKey() {
    return "Virtues";
  }
}
