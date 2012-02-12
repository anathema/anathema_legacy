package net.sf.anathema.character.sidereal.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

@RegisteredReportContent(produces = SiderealCollegeContent.class)
public class SiderealCollageContentFactory implements ReportContentFactory<SiderealCollegeContent> {

  private IResources resources;

  public SiderealCollageContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override
  public SiderealCollegeContent create(IGenericCharacter character, IGenericDescription description) {
    return new SiderealCollegeContent(character, resources);
  }
}
