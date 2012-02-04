package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.reporting.pdf.rendering.general.NullBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.lib.collection.Table;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ExtendedEncodingRegistry implements IEncodingRegistry {

  private final Table<ICharacterType, IExaltedEdition, IExtendedPartEncoder> partEncoderTable =
    new Table<ICharacterType, IExaltedEdition, IExtendedPartEncoder>();

  private final BaseFont baseFont;
  private final BaseFont symbolBaseFont;
  private ContentEncoder mutationsEncoder = new NullBoxContentEncoder("Mutations");
  private List<IVariableContentEncoder> sidebarEncoders = new ArrayList<IVariableContentEncoder>();
  private List<ITableEncoder> magicEncoders = new ArrayList<ITableEncoder>();

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

  public void setMutationsEncoder(ContentEncoder mutationsEncoder) {
    this.mutationsEncoder = mutationsEncoder;
  }

  public ContentEncoder getMutationsEncoder() {
    return mutationsEncoder;
  }

  public List<IVariableContentEncoder> getAdditionalMagicSidebarEncoders() {
    return sidebarEncoders;
  }

  public List<ITableEncoder> getAdditionalMagicEncoders() {
    return magicEncoders;
  }

  public void setPartEncoder(ICharacterType type, IExaltedEdition edition, IExtendedPartEncoder partEncoder) {
    partEncoderTable.add(type, edition, partEncoder);
  }

  public IExtendedPartEncoder getPartEncoder(ICharacterType type, IExaltedEdition edition) {
    return partEncoderTable.get(type, edition);
  }
}