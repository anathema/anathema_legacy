package net.sf.anathema.hero.intimacies.sheet.content;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.intimacies.model.IntimaciesModel;
import net.sf.anathema.hero.intimacies.model.IntimaciesModelFetcher;
import net.sf.anathema.hero.intimacies.model.Intimacy;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.sheet.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.hero.spiritual.SpiritualTraitModelFetcher;
import net.sf.anathema.hero.traits.model.types.VirtueType;
import net.sf.anathema.hero.traits.sheet.content.NamedValue;

import java.util.ArrayList;
import java.util.List;

public class ExtendedIntimaciesContent extends AbstractSubBoxContent {

  private Hero hero;

  public ExtendedIntimaciesContent(Hero hero, Resources resources) {
    super(resources);
    this.hero = hero;
  }

  @Override
  public String getHeaderKey() {
    return "Intimacies";
  }

  public int getTraitMaxValue() {
    return SpiritualTraitModelFetcher.fetch(hero).getTrait(VirtueType.Conviction).getCurrentValue();
  }

  public List<NamedValue> getPrintIntimacies() {
    List<NamedValue> printIntimacies = new ArrayList<>();
    for (Intimacy intimacy : getModel().getEntries()) {
      printIntimacies.add(new PrintIntimacy(intimacy));
    }
    return printIntimacies;
  }

  private IntimaciesModel getModel() {
    return IntimaciesModelFetcher.fetch(hero);
  }
}