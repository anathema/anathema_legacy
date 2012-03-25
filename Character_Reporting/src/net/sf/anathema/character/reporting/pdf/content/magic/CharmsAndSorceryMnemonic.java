package net.sf.anathema.character.reporting.pdf.content.magic;

import net.sf.anathema.character.generic.magic.IMagicStats;

import java.util.ArrayList;
import java.util.List;

public class CharmsAndSorceryMnemonic {

  private final List<IMagicStats> printMagic;

  public CharmsAndSorceryMnemonic(List<IMagicStats> printMagic) {
    this.printMagic = printMagic;
  }

  public void removePrintMagic(IMagicStats printMagic) {
    this.printMagic.remove(printMagic);
  }

  public List<IMagicStats> getRemainingPrintMagic() {
    return new ArrayList<IMagicStats> (printMagic);
  }
}
