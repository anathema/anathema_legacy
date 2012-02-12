package net.sf.anathema.character.reporting.pdf.content.abilities;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

@RegisteredReportContent(produces = AbilitiesContent.class)
public class AbilitiesContentFactory implements ReportContentFactory<AbilitiesContent> {
  private IResources resources;

  public AbilitiesContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override
  public AbilitiesContent create(IGenericCharacter character, IGenericDescription description) {
    return new AbilitiesContent(character, resources);
  }
}
