package net.sf.anathema.hero.experience.sheet.content;

import net.sf.anathema.hero.experience.ExperienceModelFetcher;
import net.sf.anathema.character.main.advance.ExperiencePointManagement;
import net.sf.anathema.hero.sheet.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

import java.text.MessageFormat;

public class ExperienceContent extends AbstractSubBoxContent {

  private Hero hero;

  public ExperienceContent(Resources resources, Hero hero) {
    super(resources);
    this.hero = hero;
  }

  public String getExperienceText() {
    int totalPoints = ExperienceModelFetcher.fetch(hero).getExperiencePoints().getTotalExperiencePoints();
    int spentPoints = new ExperiencePointManagement(hero).getTotalCosts();
    String experienceMessage = getResources().getString("Sheet.Experience.MessageFormat");
    return MessageFormat.format(experienceMessage, totalPoints, spentPoints, totalPoints - spentPoints);
  }

  @Override
  public String getHeaderKey() {
    return "Experience";
  }

  @Override
  public boolean hasContent() {
    return true;
  }
}
