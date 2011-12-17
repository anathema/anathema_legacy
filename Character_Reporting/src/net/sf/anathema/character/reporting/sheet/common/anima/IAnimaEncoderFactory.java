package net.sf.anathema.character.reporting.sheet.common.anima;

import net.sf.anathema.character.reporting.common.encoder.IPdfContentBoxEncoder;

public interface IAnimaEncoderFactory {

  public IPdfContentBoxEncoder createAnimaEncoder();
}
