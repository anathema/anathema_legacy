package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.reporting.pdf.rendering.general.NullPdfContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IPdfVariableContentBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.IPdfTableEncoder;
import net.sf.anathema.lib.collection.Table;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ExtendedEncodingRegistry {

  private final Table<ICharacterType, IExaltedEdition, IExtendedPartEncoder> partEncoderTable = new Table<ICharacterType, IExaltedEdition, IExtendedPartEncoder>();

  private final BaseFont baseFont;
  private final BaseFont symbolBaseFont;
  private IPdfContentBoxEncoder weaponContentEncoder = new NullPdfContentEncoder("Weapons");
  private IPdfContentBoxEncoder armourContentEncoder = new NullPdfContentEncoder("Armour");
  private IPdfContentBoxEncoder intimaciesEncoder = new NullPdfContentEncoder("Intimacies");
  private IPdfContentBoxEncoder possessionsEncoder = new NullPdfContentEncoder("Possessions");
  private IPdfContentBoxEncoder linguisticsEncoder = new NullPdfContentEncoder("Linguistics");
  private IPdfContentBoxEncoder mutationsEncoder = new NullPdfContentEncoder("Mutations");
  private IPdfContentBoxEncoder thaumaturgyEncoder = new NullPdfContentEncoder("Thaumaturgy");
  private IPdfContentBoxEncoder meritsAndFlawsEncoder = new NullPdfContentEncoder("Merits & Flaws");
  private List<IPdfVariableContentBoxEncoder> sidebarEncoders = new ArrayList<IPdfVariableContentBoxEncoder>();
  private List<IPdfTableEncoder> magicEncoders = new ArrayList<IPdfTableEncoder>();

  public ExtendedEncodingRegistry() {
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

  public void setPartEncoder(ICharacterType type, IExaltedEdition edition, IExtendedPartEncoder partEncoder) {
    partEncoderTable.add(type, edition, partEncoder);
  }

  public IExtendedPartEncoder getPartEncoder(ICharacterType type, IExaltedEdition edition) {
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
