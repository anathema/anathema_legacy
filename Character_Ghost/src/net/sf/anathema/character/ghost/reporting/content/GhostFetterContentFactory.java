package net.sf.anathema.character.ghost.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

@RegisteredReportContent(produces = GhostFetterContent.class)
public class GhostFetterContentFactory implements ReportContentFactory<GhostFetterContent> {
  private IResources resources;

  public GhostFetterContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override

  public GhostFetterContent create(IGenericCharacter character, IGenericDescription description) {
    return new GhostFetterContent(resources, character);
  }
}
