package net.sf.anathema.hero.concept.display.caste.presenter;

import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.namegenerator.domain.INameGenerator;

public class NameGeneratorCommand implements Command {
  private final ITextualDescription description;
  private final INameGenerator generator;

  public NameGeneratorCommand(ITextualDescription description, INameGenerator generator) {
    this.description = description;
    this.generator = generator;
  }

  @Override
  public void execute() {
    String[] names = generator.createNames(1);
    description.setText(names[0]);
  }
}