package net.sf.anathema.character.reporting.pdf.content.magic;

import net.sf.anathema.character.generic.magic.IMagicStats;

import java.util.ArrayList;
import java.util.List;

public class AbstractMagicMnemonic implements MagicMnemonic {

  private final List<IMagicStats> printMagic;

  public AbstractMagicMnemonic(List<IMagicStats> printMagic) {
    this.printMagic = printMagic;
  }

  @Override
  public void removePrintMagic(IMagicStats printMagic) {
    this.printMagic.remove(printMagic);
  }

  @Override
  public List<IMagicStats> getRemainingPrintMagic() {
    return new ArrayList<IMagicStats> (printMagic);
  }
}
