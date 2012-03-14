package net.sf.anathema.character.generic.impl.rules;

import net.sf.anathema.character.generic.rules.IExaltedSourceBook;

public enum ExaltedSourceBook implements IExaltedSourceBook {
 FirstEdition(), Bo3C(), SavantSorcerer(),
 BoneEbony(), Abyssals1st(), DragonBlooded1st(),
 Lunars1st(), Sidereals1st(), TimeOfTumult(),
 SavageSeas(), BloodSalt(), GamesOfDivinity(),
 Outcaste(), Illuminated(), ABAir(),
 ABEarth(), ABFire(), ABWater(),
 ABWood(), CBDawn(), CBZenith(),
 CBTwilight(), CBNight(), CBEclipse(),
 PlayersGuide(), SecondEdition(), DragonBlooded2nd(),
 Lunars2nd(), Sidereals2nd(), Abyssals2nd(),
 Infernals(), Alchemicals2nd(), WondersLostAge(),
 ScrollMonk(), ImperfectLotus(),
 WhiteTreatise(), BlackTreatise(), OadenolsCodex(),
 LandsOfCreation(),LordsOfCreation(), Comic0(),
 ThousandCorrectActions(),GloriesLuna(),
 GloriesMaidens(),GloriesUCS(), DebrisFallenRaces(),
 ScrollExalts(), InkMonkeys(),ReturnEmpress(),
 UnderRose(), ContagionLaw(), ScrollErrata(),
 GodsAndElementals(), GhostsAndDemons(),
 CompassWyld(), BrokenWingedCrane(), LostDead(),
 CompassAutochthonia(),  CompassWest(), CompassEast();

  @Override
  public String getId() {
    return name();
  }
}