package net.sf.anathema.character.reporting.pdf.layout.simple;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.collection.Table;

public class SimpleEncodingRegistry {

  private final Table<ICharacterType, IExaltedEdition, ISimplePartEncoder> partEncoderTable =
    new Table<ICharacterType, IExaltedEdition, ISimplePartEncoder>();

  public void setPartEncoder(ICharacterType type, IExaltedEdition edition, ISimplePartEncoder partEncoder) {
    partEncoderTable.add(type, edition, partEncoder);
  }

  public ISimplePartEncoder getPartEncoder(ICharacterType type, IExaltedEdition edition) {
    return partEncoderTable.get(type, edition);
  }
}
