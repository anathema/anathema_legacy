package net.sf.anathema.character.reporting.sheet.common.anima;

import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;

public interface IAnimaEncoderFactory {

  public IPdfContentBoxEncoder createAnimaEncoder();
}