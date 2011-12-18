package net.sf.anathema.character.reporting.pdf.layout.simple;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.reporting.pdf.layout.extended.IEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.collection.Table;

import java.awt.*;

public class SimpleEncodingRegistry implements IEncodingRegistry {

  private final Table<ICharacterType, IExaltedEdition, ISimplePartEncoder> partEncoderTable = new Table<ICharacterType, IExaltedEdition,
    ISimplePartEncoder>();

  private final BaseFont baseFont;
  private final BaseFont symbolBaseFont;
  private IBoxContentEncoder weaponContentEncoder;
  private IBoxContentEncoder armourContentEncoder;
  private IBoxContentEncoder intimaciesEncoder;
  private IBoxContentEncoder possessionsEncoder;
  private IBoxContentEncoder linguisticsEncoder;
  private IBoxContentEncoder mutationsEncoder;
  private IBoxContentEncoder thaumaturgyEncoder;
  private IBoxContentEncoder meritsAndFlawsEncoder;

  public SimpleEncodingRegistry() {
    this.baseFont = new Font(Font.HELVETICA, 7, Font.NORMAL, Color.BLACK).getCalculatedBaseFont(true);
    this.symbolBaseFont = new Font(Font.SYMBOL, 7, Font.NORMAL, Color.BLACK).getCalculatedBaseFont(false);
  }

  public BaseFont getBaseFont() {
    return baseFont;
  }

  public BaseFont getSymbolBaseFont() {
    return symbolBaseFont;
  }

  public void setWeaponContentEncoder(IBoxContentEncoder encoder) {
    this.weaponContentEncoder = encoder;
  }

  public void setArmourContentEncoder(IBoxContentEncoder encoder) {
    this.armourContentEncoder = encoder;
  }

  public void setIntimaciesEncoder(IBoxContentEncoder intimaciesEncoder) {
    this.intimaciesEncoder = intimaciesEncoder;
  }

  public void setMutationsEncoder(IBoxContentEncoder mutationsEncoder) {
    this.mutationsEncoder = mutationsEncoder;
  }

  public void setThaumaturgyEncoder(IBoxContentEncoder thaumaturgyEncoder) {
    this.thaumaturgyEncoder = thaumaturgyEncoder;
  }

  public void setMeritsAndFlawsEncoder(IBoxContentEncoder meritsAndFlawsEncoder) {
    this.meritsAndFlawsEncoder = meritsAndFlawsEncoder;
  }

  public IBoxContentEncoder getWeaponContentEncoder() {
    return weaponContentEncoder;
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

  public IBoxContentEncoder getThaumaturgyEncoder() {
    return thaumaturgyEncoder;
  }

  public IBoxContentEncoder getMeritsAndFlawsEncoder() {
    return meritsAndFlawsEncoder;
  }

  public void setPartEncoder(ICharacterType type, IExaltedEdition edition, ISimplePartEncoder partEncoder) {
    partEncoderTable.add(type, edition, partEncoder);
  }

  public ISimplePartEncoder getPartEncoder(ICharacterType type, IExaltedEdition edition) {
    return partEncoderTable.get(type, edition);
  }

  public boolean hasPartEncoder(ICharacterType type, IExaltedEdition edition) {
    return partEncoderTable.contains(type, edition);
  }

  public void setPossessionsEncoder(IBoxContentEncoder encoder) {
    this.possessionsEncoder = encoder;
  }

  public void setLinguisticsEncoder(IBoxContentEncoder encoder) {
    this.linguisticsEncoder = encoder;
  }
}
