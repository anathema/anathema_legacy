package net.sf.anathema.character.generic.impl.rules;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedSourceBook;

public enum ExaltedSourceBook implements IExaltedSourceBook {
  FirstEdition(ExaltedEdition.FirstEdition), Bo3C(ExaltedEdition.FirstEdition), SavantSorcerer(
      ExaltedEdition.FirstEdition), BoneEbony(ExaltedEdition.FirstEdition), Abyssals1st(ExaltedEdition.FirstEdition),
  DragonBlooded1st(ExaltedEdition.FirstEdition), Lunars1st(ExaltedEdition.FirstEdition), Sidereals1st(
      ExaltedEdition.FirstEdition), TimeOfTumult(ExaltedEdition.FirstEdition), SavageSeas(ExaltedEdition.FirstEdition),
  BloodSalt(ExaltedEdition.FirstEdition), GamesOfDivinity(ExaltedEdition.FirstEdition), Outcaste(
      ExaltedEdition.FirstEdition), Illuminated(ExaltedEdition.FirstEdition), ABAir(ExaltedEdition.FirstEdition),
  ABEarth(ExaltedEdition.FirstEdition), ABFire(ExaltedEdition.FirstEdition), ABWater(ExaltedEdition.FirstEdition),
  ABWood(ExaltedEdition.FirstEdition), CBDawn(ExaltedEdition.FirstEdition), CBZenith(ExaltedEdition.FirstEdition),
  CBTwilight(ExaltedEdition.FirstEdition), CBNight(ExaltedEdition.FirstEdition),
  CBEclipse(ExaltedEdition.FirstEdition), PlayersGuide(ExaltedEdition.FirstEdition), SecondEdition(
      ExaltedEdition.SecondEdition), DragonBlooded2nd(ExaltedEdition.SecondEdition), Lunars2nd(
      ExaltedEdition.SecondEdition), Sidereals2nd(ExaltedEdition.SecondEdition), Abyssals2nd(ExaltedEdition.SecondEdition), WondersLostAge(
      ExaltedEdition.SecondEdition), ScrollMonk(ExaltedEdition.SecondEdition), ImperfectLotus(
      ExaltedEdition.SecondEdition), WhiteTreatise(ExaltedEdition.SecondEdition), BlackTreatise(
      ExaltedEdition.SecondEdition), OadenolsCodex(ExaltedEdition.SecondEdition), LandsOfCreation(ExaltedEdition.SecondEdition), LordsOfCreation(ExaltedEdition.SecondEdition), Comic0(ExaltedEdition.FirstEdition);

  private final IExaltedEdition edition;

  private ExaltedSourceBook(IExaltedEdition edition) {
    this.edition = edition;
  }

  public String getId() {
    return name();
  }

  public IExaltedEdition getEdition() {
    return edition;
  }

  public static IExaltedSourceBook[] getSourcesForEdition(IExaltedEdition requestedEdition) {
    List<ExaltedSourceBook> books = new ArrayList<ExaltedSourceBook>();
    for (ExaltedSourceBook book : values()) {
      if (book.getEdition() == requestedEdition) {
        books.add(book);
      }
    }
    return books.toArray(new IExaltedSourceBook[books.size()]);
  }
}