package net.sf.anathema.charmentry.model;

import net.sf.anathema.lib.util.IIdentificate;

public enum ExaltedSourceBooks implements IIdentificate {
  FirstEdition, Abyssals1st, DragonBlooded1st, Lunars1st, Sidereals1st, TimeOfTumult, SavageSeas, Outcaste,
  Illuminated, ABAir, ABEarth, ABFire, ABWater, ABWood, CBDawn, CBZenith, CBTwilight, CBNight, CBEclipse, PlayersGuide,
  SecondEdition, WondersLostAge, Comic0;

  public String getId() {
    return name();
  }
}