package net.sf.anathema.charmentry.model;

import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.lib.util.IIdentificate;

public enum ExaltedSourceBook implements IIdentificate {
  FirstEdition(ExaltedEdition.FirstEdition), Abyssals1st(ExaltedEdition.FirstEdition), DragonBlooded1st(
      ExaltedEdition.FirstEdition), Lunars1st(ExaltedEdition.FirstEdition), Sidereals1st(ExaltedEdition.FirstEdition),
  TimeOfTumult(ExaltedEdition.FirstEdition), SavageSeas(ExaltedEdition.FirstEdition), Outcaste(
      ExaltedEdition.FirstEdition), Illuminated(ExaltedEdition.FirstEdition), ABAir(ExaltedEdition.FirstEdition),
  ABEarth(ExaltedEdition.FirstEdition), ABFire(ExaltedEdition.FirstEdition), ABWater(ExaltedEdition.FirstEdition),
  ABWood(ExaltedEdition.FirstEdition), CBDawn(ExaltedEdition.FirstEdition), CBZenith(ExaltedEdition.FirstEdition),
  CBTwilight(ExaltedEdition.FirstEdition), CBNight(ExaltedEdition.FirstEdition),
  CBEclipse(ExaltedEdition.FirstEdition), PlayersGuide(ExaltedEdition.FirstEdition), SecondEdition(
      ExaltedEdition.SecondEdition), WondersLostAgeSecondEdition(ExaltedEdition.FirstEdition), Comic0(
      ExaltedEdition.FirstEdition);

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
}