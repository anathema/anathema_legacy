package net.sf.anathema.character.reporting.extended;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.reporting.common.encoder.IPdfTableEncoder;
import net.sf.anathema.character.reporting.common.encoder.IPdfVariableContentBoxEncoder;
import net.sf.anathema.character.reporting.extended.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.extended.page.IPdfPartEncoder;
import net.sf.anathema.lib.collection.Table;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PdfEncodingRegistry {

  private final Table<ICharacterType, IExaltedEdition, IPdfPartEncoder> partEncoderTable = new Table<ICharacterType, IExaltedEdition,
    IPdfPartEncoder>();
  private final BaseFont baseFont;
  private final BaseFont symbolBaseFont;
  private IPdfContentBoxEncoder weaponContentEncoder;
  private IPdfContentBoxEncoder armourContentEncoder;
  private IPdfContentBoxEncoder intimaciesEncoder;
  private IPdfContentBoxEncoder possessionsEncoder;
  private IPdfContentBoxEncoder linguisticsEncoder;
  private IPdfContentBoxEncoder mutationsEncoder;
  private IPdfContentBoxEncoder thaumaturgyEncoder;
  private List<IPdfVariableContentBoxEncoder> sidebarEncoders = new ArrayList<IPdfVariableContentBoxEncoder>();
  private List<IPdfTableEncoder> magicEncoders = new ArrayList<IPdfTableEncoder>();
  private IPdfContentBoxEncoder meritsAndFlawsEncoder;

  public PdfEncodingRegistry() {
    this.baseFont = new Font(Font.HELVETICA, 7, Font.NORMAL, Color.BLACK).getCalculatedBaseFont(true);
    this.symbolBaseFont = new Font(Font.SYMBOL, 7, Font.NORMAL, Color.BLACK).getCalculatedBaseFont(true);
  }

  public BaseFont getBaseFont() {
    return baseFont;
  }

  public BaseFont getSymbolBaseFont() {
    return symbolBaseFont;
  }

  public void setWeaponContentEncoder(IPdfContentBoxEncoder encoder) {
    this.weaponContentEncoder = encoder;
  }

  public void setArmourContentEncoder(IPdfContentBoxEncoder encoder) {
    this.armourContentEncoder = encoder;
  }

  public void setIntimaciesEncoder(IPdfContentBoxEncoder intimaciesEncoder) {
    this.intimaciesEncoder = intimaciesEncoder;
  }

  public void setMutationsEncoder(IPdfContentBoxEncoder mutationsEncoder) {
    this.mutationsEncoder = mutationsEncoder;
  }

  public void setThaumaturgyEncoder(IPdfContentBoxEncoder thaumaturgyEncoder) {
    this.thaumaturgyEncoder = thaumaturgyEncoder;
  }

  public void setMeritsAndFlawsEncoder(IPdfContentBoxEncoder meritsAndFlawsEncoder) {
    this.meritsAndFlawsEncoder = meritsAndFlawsEncoder;
  }

  public void addAdditionalMagicSidebarEncoder(IPdfVariableContentBoxEncoder encoder) {
    sidebarEncoders.add(encoder);
  }

  public void addAdditionalMagicEncoder(IPdfTableEncoder encoder) {
    magicEncoders.add(encoder);
  }

  public IPdfContentBoxEncoder getWeaponContentEncoder() {
    return weaponContentEncoder;
  }

  public IPdfContentBoxEncoder getArmourContentEncoder() {
    return armourContentEncoder;
  }

  public IPdfContentBoxEncoder getIntimaciesEncoder() {
    return intimaciesEncoder;
  }

  public IPdfContentBoxEncoder getPossessionsEncoder() {
    return possessionsEncoder;
  }

  public IPdfContentBoxEncoder getLinguisticsEncoder() {
    return linguisticsEncoder;
  }

  public IPdfContentBoxEncoder getMutationsEncoder() {
    return mutationsEncoder;
  }

  public IPdfContentBoxEncoder getThaumaturgyEncoder() {
    return thaumaturgyEncoder;
  }

  public List<IPdfVariableContentBoxEncoder> getAdditionalMagicSidebarEncoders() {
    return sidebarEncoders;
  }

  public List<IPdfTableEncoder> getAdditionalMagicEncoders() {
    return magicEncoders;
  }

  public IPdfContentBoxEncoder getMeritsAndFlawsEncoder() {
    return meritsAndFlawsEncoder;
  }

  public void setPartEncoder(ICharacterType type, IExaltedEdition edition, IPdfPartEncoder partEncoder) {
    partEncoderTable.add(type, edition, partEncoder);
  }

  public IPdfPartEncoder getPartEncoder(ICharacterType type, IExaltedEdition edition) {
    return partEncoderTable.get(type, edition);
  }

  public boolean hasPartEncoder(ICharacterType type, IExaltedEdition edition) {
    return partEncoderTable.contains(type, edition);
  }

  public void setPossessionsEncoder(IPdfContentBoxEncoder encoder) {
    this.possessionsEncoder = encoder;
  }

  public void setLinguisticsEncoder(IPdfContentBoxEncoder encoder) {
    this.linguisticsEncoder = encoder;
  }
}
