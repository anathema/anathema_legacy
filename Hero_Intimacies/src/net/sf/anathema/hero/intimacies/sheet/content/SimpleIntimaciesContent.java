package net.sf.anathema.hero.intimacies.sheet.content;

import net.sf.anathema.hero.sheet.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.hero.sheet.pdf.content.ListSubBoxContent;
import net.sf.anathema.hero.intimacies.model.IntimaciesModel;
import net.sf.anathema.hero.intimacies.model.IntimaciesModelFetcher;
import net.sf.anathema.hero.intimacies.model.Intimacy;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.List;

public class SimpleIntimaciesContent extends AbstractSubBoxContent implements ListSubBoxContent {

  private Hero hero;

  public SimpleIntimaciesContent(Hero hero, Resources resources) {
    super(resources);
    this.hero = hero;
  }

  @Override
  public String getHeaderKey() {
    return "Intimacies";
  }

  @Override
  public List<String> getPrintEntries() {
    List<String> printIntimacies = new ArrayList<>();
    for (Intimacy intimacy : getModel().getEntries()) {
      String text = intimacy.getName();
      if (!intimacy.isComplete()) {
        text += " (" + intimacy.getTrait().getCurrentValue() + "/" + intimacy.getTrait().getMaximalValue() + ")";
      }
      printIntimacies.add(text);
    }
    return printIntimacies;
  }

  private IntimaciesModel getModel() {
    return IntimaciesModelFetcher.fetch(hero);
  }
}
