package net.sf.anathema.hero.concept.display.description;

import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface NameEditAction {
  void configure(Tool tool, ITextualDescription description);
}