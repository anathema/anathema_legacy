package net.sf.anathema.character.reporting.pdf.content.virtues;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.character.reporting.pdf.content.general.NamedValue;
import net.sf.anathema.character.reporting.pdf.content.general.PrintTrait;
import net.sf.anathema.lib.resources.Resources;

public class VirtueContent extends AbstractSubBoxContent {

  private IGenericCharacter character;

  public VirtueContent(Resources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
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
    IGenericTraitCollection virtueCollection = getVirtueCollection();
    IGenericTrait virtue = virtueCollection.getTrait(type);
    Resources resources = getResources();
    return new PrintTrait(resources, virtue);
  }

  private IGenericTraitCollection getVirtueCollection() {
    return character.getTraitCollection();
  }

  @Override
  public String getHeaderKey() {
    return "Virtues";
  }
}