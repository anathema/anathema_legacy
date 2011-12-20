package net.sf.anathema.character.reporting.pdf.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;

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

  public <C extends SubContent> C createSubContent(Class<C> contentClass) {
    ReportContentFactory<C> factory = registry.getFactory(contentClass);
    return factory.create(character, description);
  }
}
