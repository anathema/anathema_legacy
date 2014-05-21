package net.sf.anathema.hero.spiritual.sheet.virtues.content;

import net.sf.anathema.hero.traits.model.ValuedTraitType;
import net.sf.anathema.hero.traits.model.types.VirtueType;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.sheet.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.hero.spiritual.SpiritualTraitModelFetcher;
import net.sf.anathema.hero.traits.model.TraitMap;
import net.sf.anathema.hero.traits.sheet.content.NamedValue;
import net.sf.anathema.hero.traits.sheet.content.PrintTrait;
import net.sf.anathema.framework.environment.Resources;

public class VirtueContent extends AbstractSubBoxContent {

  private Hero hero;

  public VirtueContent(Resources resources, Hero hero) {
    super(resources);
    this.hero = hero;
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
    TraitMap virtueCollection = getVirtueCollection();
    ValuedTraitType virtue = virtueCollection.getTrait(type);
    Resources resources = getResources();
    return new PrintTrait(resources, virtue);
  }

  private TraitMap getVirtueCollection() {
    return SpiritualTraitModelFetcher.fetch(hero);
  }

  @Override
  public String getHeaderKey() {
    return "Virtues";
  }
}