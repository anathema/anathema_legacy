package net.sf.anathema.character.reporting.pdf.rendering.boxes.anima;

import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.SMALLER_FONT_SIZE;

public abstract class AbstractAnimaEncoderFactory extends AbstractEncoderFactory {

  public AbstractAnimaEncoderFactory() {
    super(EncoderIds.ANIMA);
  }

  @Override
  public ContentEncoder create(IResources resources, BasicContent content) {
    return new GenericAnimaEncoder(resources, getFontSize(), getAnimaTableEncoder(resources));
  }

  protected abstract ITableEncoder getAnimaTableEncoder(IResources resources);

  protected final float getFontSize() {
    return SMALLER_FONT_SIZE;
  }
}
