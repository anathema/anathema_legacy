package net.sf.anathema.character.reporting.pdf.rendering.boxes;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

public interface EncoderFactory extends Identifier {

  ContentEncoder create(Resources resources, BasicContent content);

  boolean supports(BasicContent content);

  float getPreferredHeight(EncodingMetrics metrics, float width);
}
