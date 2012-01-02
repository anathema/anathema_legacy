package net.sf.anathema.character.db.reporting;

import net.sf.anathema.character.reporting.pdf.rendering.boxes.anima.AbstractAnimaEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.anima.AnimaTableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.lib.resources.IResources;

public class DbAnimaEncoderFactory extends AbstractAnimaEncoderFactory {

  public DbAnimaEncoderFactory(IResources resources) {
    super(resources);
  }

  @Override
  protected ITableEncoder getAnimaTableEncoder() {
    return new AnimaTableEncoder(getResources(), getFontSize(), new DbAnimaTableRangeProvider());
  }
}
