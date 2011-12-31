package net.sf.anathema.character.db.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

public class Db2ndEditionGreatCurseContentFactory implements ReportContentFactory<Db2ndEditionGreatCurseContent> {

  private IResources resources;

  public Db2ndEditionGreatCurseContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override
  public Db2ndEditionGreatCurseContent create(IGenericCharacter character, IGenericDescription description) {
    return new Db2ndEditionGreatCurseContent(resources, character);
  }
}
