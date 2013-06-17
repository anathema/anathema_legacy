package net.sf.anathema.hero.experience.sheet.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.lib.resources.Resources;

import java.text.MessageFormat;

public class ExperienceContent extends AbstractSubBoxContent {

  private IGenericCharacter character;

  public ExperienceContent(Resources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  public String getExperienceText() {
    int totalPoints = character.getTotalExperiencePoints();
    int spentPoints = character.getSpentExperiencePoints();
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
