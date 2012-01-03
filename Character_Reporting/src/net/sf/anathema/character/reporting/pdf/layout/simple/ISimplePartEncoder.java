package net.sf.anathema.character.reporting.pdf.layout.simple;

import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;

public interface ISimplePartEncoder {

  ContentEncoder getAnimaEncoder();

  ContentEncoder getGreatCurseEncoder();
}
