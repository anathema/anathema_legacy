package net.sf.anathema.character.reporting.common.boxes.anima;

import net.sf.anathema.character.reporting.common.encoder.IPdfContentBoxEncoder;

public interface IAnimaEncoderFactory {

  public IPdfContentBoxEncoder createAnimaEncoder();
}
