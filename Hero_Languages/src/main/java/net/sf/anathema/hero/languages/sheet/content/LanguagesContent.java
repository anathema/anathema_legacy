package net.sf.anathema.hero.languages.sheet.content;

import net.sf.anathema.hero.languages.model.LanguagesModel;
import net.sf.anathema.hero.languages.model.LanguagesModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.sheet.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.hero.sheet.pdf.content.ListSubBoxContent;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class LanguagesContent extends AbstractSubBoxContent implements ListSubBoxContent {

  private Hero hero;

  public LanguagesContent(Hero hero, Resources resources) {
    super(resources);
    this.hero = hero;
  }

  @Override
  public String getHeaderKey() {
    return "Languages";
  }

  @Override
  public List<String> getPrintEntries() {
    List<String> printLanguages = new ArrayList<>();
    LanguagesModel model = getModel();
    for (Identifier language : model.getEntries()) {
      String text = language.getId();
      if (model.isPredefinedLanguage(language)) {
        text = getString("Language." + text);
      }
      printLanguages.add(text);
    }
    return printLanguages;
  }

  private LanguagesModel getModel() {
    return LanguagesModelFetcher.fetch(hero);
  }
}
