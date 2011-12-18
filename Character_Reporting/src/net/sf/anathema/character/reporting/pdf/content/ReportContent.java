package net.sf.anathema.character.reporting.pdf.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.lib.resources.IResources;

public class ReportContent {

  private final IGenericCharacter character;
  private final IGenericDescription description;
  private final ReportContentRegistry registry;

  public ReportContent(ReportContentRegistry registry, IGenericCharacter character, IGenericDescription description) {
    this.registry = registry;
    this.character = character;
    this.description = description;
  }

  public IGenericCharacter getCharacter() {
    return character;
  }

  public IGenericDescription getDescription() {
    return description;
  }

  public <C extends ISubContent> C createSubContent(Class<C> contentClass) {
    IReportContentFactory<C> factory = registry.getFactory(contentClass);
    return factory.create(character, description);
  }
}
