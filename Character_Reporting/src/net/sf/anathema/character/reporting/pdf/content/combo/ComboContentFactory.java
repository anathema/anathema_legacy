package net.sf.anathema.character.reporting.pdf.content.combo;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

@RegisteredReportContent(produces = ComboContent.class)
public class ComboContentFactory implements ReportContentFactory<ComboContent> {
  private IResources resources;

  public ComboContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override
  public ComboContent create(IGenericCharacter character, IGenericDescription description) {
    return new ComboContent(character, resources);
  }
}
