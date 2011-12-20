package net.sf.anathema.character.reporting.pdf.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;

public interface ReportContentFactory<C extends SubContent> {

  C create(IGenericCharacter character, IGenericDescription description);
}
