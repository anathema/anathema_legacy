package net.sf.anathema.character.reporting.pdf.content.stats.anima;

import net.sf.anathema.character.generic.character.IGenericCharacter;

public interface IAnimaTableRangeProvider {

  String getRange(int level, IGenericCharacter character);
}
