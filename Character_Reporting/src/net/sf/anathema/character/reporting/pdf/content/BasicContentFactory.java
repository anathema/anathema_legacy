package net.sf.anathema.character.reporting.pdf.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.lib.resources.IResources;

@RegisteredReportContent(produces = BasicContent.class)
public class BasicContentFactory implements ReportContentFactory<BasicContent> {

  @SuppressWarnings("UnusedDeclaration")
  public BasicContentFactory(IResources resources) {
    //Just to adhere to protocol
  }

  @Override
  public BasicContent create(IGenericCharacter character, IGenericDescription description) {
    return new BasicContent(character);
  }
}