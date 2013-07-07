package net.sf.anathema.hero.intimacies.sheet.content;

import net.sf.anathema.character.main.framework.configuration.AnathemaCharacterPreferences;
import net.sf.anathema.character.main.traits.types.VirtueType;
import net.sf.anathema.hero.othertraits.OtherTraitModelFetcher;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.character.reporting.pdf.content.general.NamedValue;
import net.sf.anathema.hero.intimacies.model.IntimaciesModel;
import net.sf.anathema.hero.intimacies.model.IntimaciesModelFetcher;
import net.sf.anathema.hero.intimacies.model.Intimacy;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

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
    return OtherTraitModelFetcher.fetch(hero).getTrait(VirtueType.Conviction).getCurrentValue();
  }

  public List<NamedValue> getPrintIntimacies() {
    List<NamedValue> printIntimacies = new ArrayList<>();
    for (Intimacy intimacy : getModel().getEntries()) {
      if (!isPrintZeroIntimacies() && intimacy.getTrait().getCurrentValue() == 0) {
        continue;
      }
      printIntimacies.add(new PrintIntimacy(intimacy));
    }
    return printIntimacies;
  }

  private IntimaciesModel getModel() {
    return IntimaciesModelFetcher.fetch(hero);
  }

  private boolean isPrintZeroIntimacies() {
    return AnathemaCharacterPreferences.getDefaultPreferences().printZeroIntimacies();
  }
}
