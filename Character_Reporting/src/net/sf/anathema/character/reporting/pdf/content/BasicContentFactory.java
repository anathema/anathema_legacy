package net.sf.anathema.character.reporting.pdf.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;

public class BasicContentFactory implements ReportContentFactory<BasicContent> {

  @Override
  public BasicContent create(IGenericCharacter character, IGenericDescription description) {
    return new BasicContent(character);
  }
}
