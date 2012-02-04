package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.collection.Table;

import java.awt.Color;

public class ExtendedEncodingRegistry implements IEncodingRegistry {

  private final Table<ICharacterType, IExaltedEdition, IExtendedPartEncoder> partEncoderTable =
    new Table<ICharacterType, IExaltedEdition, IExtendedPartEncoder>();

  private final BaseFont baseFont;
  private final BaseFont symbolBaseFont;

  public ExtendedEncodingRegistry() {
    this.baseFont = new Font(Font.HELVETICA, 7, Font.NORMAL, Color.BLACK).getCalculatedBaseFont(true);
    this.symbolBaseFont = new Font(Font.SYMBOL, 7, Font.NORMAL, Color.BLACK).getCalculatedBaseFont(true);
  }

  @Override
  public BaseFont getBaseFont() {
    return baseFont;
  }

  @Override
  public BaseFont getSymbolBaseFont() {
    return symbolBaseFont;
  }

  public void setPartEncoder(ICharacterType type, IExaltedEdition edition, IExtendedPartEncoder partEncoder) {
    partEncoderTable.add(type, edition, partEncoder);
  }

  public IExtendedPartEncoder getPartEncoder(ICharacterType type, IExaltedEdition edition) {
    return partEncoderTable.get(type, edition);
  }
}