package net.sf.anathema.character.reporting.sheet.second.equipment;

public class WeaponEncodingUtilities {

  public static Float[] createStandardColumnWeights(int columnCount) {
    Float[] columnWeights = new Float[columnCount];
    for (int index = 0; index < columnWeights.length; index++) {
      columnWeights[index] = new Float(1);
    }
    return columnWeights;
  }
}