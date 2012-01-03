package net.sf.anathema.character.reporting.pdf.rendering.boxes;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

public interface EncoderFactory extends IIdentificate {

  ContentEncoder create(IResources resources, BasicContent content);

  boolean supports(BasicContent content);

  boolean hasAttribute(EncoderAttributeType type);

  float getValue(BasicContent content, EncoderAttributeType type);
}
