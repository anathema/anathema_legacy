package net.sf.anathema.character.reporting.pdf.content.experience;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.lib.resources.IResources;

import java.text.MessageFormat;

public class ExperienceContent extends AbstractSubBoxContent {

  private IGenericCharacter character;

  public ExperienceContent(IResources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  public String getExperienceText() {
    int totalPoints = character.getTotalExperiencePoints();
    int spentPoints = character.getSpentExperiencePoints();
    String experienceMessage = getResources().getString("Sheet.Experience.MessageFormat"); //$NON-NLS-1$
    return MessageFormat.format(experienceMessage, totalPoints, spentPoints, totalPoints - spentPoints);
  }

  @Override
  public String getHeaderKey() {
    return "Experience"; //$NON-NLS-1$
  }

  @Override
  public boolean hasContent() {
    return true;
  }
}
