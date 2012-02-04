package net.sf.anathema.character.reporting.pdf.layout.extended;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.collection.Table;

public class ExtendedEncodingRegistry {

  private final Table<ICharacterType, IExaltedEdition, IExtendedPartEncoder> partEncoderTable =
    new Table<ICharacterType, IExaltedEdition, IExtendedPartEncoder>();

  public void setPartEncoder(ICharacterType type, IExaltedEdition edition, IExtendedPartEncoder partEncoder) {
    partEncoderTable.add(type, edition, partEncoder);
  }

  public IExtendedPartEncoder getPartEncoder(ICharacterType type, IExaltedEdition edition) {
    return partEncoderTable.get(type, edition);
  }
}