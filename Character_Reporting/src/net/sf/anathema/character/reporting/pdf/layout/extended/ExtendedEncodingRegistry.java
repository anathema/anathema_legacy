package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.reporting.pdf.rendering.general.NullBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableBoxContentEncoder;
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
  private IBoxContentEncoder armourContentEncoder = new NullBoxContentEncoder("Armour");
  private IBoxContentEncoder intimaciesEncoder = new NullBoxContentEncoder("Intimacies");
  private IBoxContentEncoder possessionsEncoder = new NullBoxContentEncoder("Possessions");
  private IBoxContentEncoder linguisticsEncoder = new NullBoxContentEncoder("Linguistics");
  private IBoxContentEncoder mutationsEncoder = new NullBoxContentEncoder("Mutations");
  private List<IVariableBoxContentEncoder> sidebarEncoders = new ArrayList<IVariableBoxContentEncoder>();
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

  @Override
  public void setArmourContentEncoder(IBoxContentEncoder encoder) {
    this.armourContentEncoder = encoder;
  }

  @Override
  public void setIntimaciesEncoder(IBoxContentEncoder intimaciesEncoder) {
    this.intimaciesEncoder = intimaciesEncoder;
  }

  public void setMutationsEncoder(IBoxContentEncoder mutationsEncoder) {
    this.mutationsEncoder = mutationsEncoder;
  }

  public IBoxContentEncoder getArmourContentEncoder() {
    return armourContentEncoder;
  }

  public IBoxContentEncoder getIntimaciesEncoder() {
    return intimaciesEncoder;
  }

  public IBoxContentEncoder getPossessionsEncoder() {
    return possessionsEncoder;
  }

  public IBoxContentEncoder getLinguisticsEncoder() {
    return linguisticsEncoder;
  }

  public IBoxContentEncoder getMutationsEncoder() {
    return mutationsEncoder;
  }

  public List<IVariableBoxContentEncoder> getAdditionalMagicSidebarEncoders() {
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

  public boolean hasPartEncoder(ICharacterType type, IExaltedEdition edition) {
    return partEncoderTable.contains(type, edition);
  }

  @Override
  public void setPossessionsEncoder(IBoxContentEncoder encoder) {
    this.possessionsEncoder = encoder;
  }

  public void setLinguisticsEncoder(IBoxContentEncoder encoder) {
    this.linguisticsEncoder = encoder;
  }
}
