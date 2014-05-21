package net.sf.anathema.hero.concept.display.description;

import net.sf.anathema.character.framework.CharacterUI;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.environment.dependencies.Weight;
import net.sf.anathema.hero.concept.display.caste.presenter.NameGeneratorCommand;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.namegenerator.exalted.domain.ThresholdNameGenerator;

@Weight(weight = 2)
@RegisteredNameEditAction
public class ThresholdNameAction implements NameEditAction {

  private Resources resources;

  public ThresholdNameAction(Resources resources) {
    this.resources = resources;
  }

  @Override
  public void configure(Tool tool, ITextualDescription description) {
    tool.setIcon(new CharacterUI().getRandomThresholdNameIconPath());
    tool.setTooltip(resources.getString("CharacterDescription.Tooltip.ThresholdName"));
    tool.setCommand(new NameGeneratorCommand(description, new ThresholdNameGenerator()));
  }
}