package net.sf.anathema.development.reporting.generation;

import java.io.File;

import net.sf.anathema.framework.reporting.jasper.IJasperReport;
import net.sf.anathema.framework.repository.IItem;

public interface IGenerationData {

  public IItem createFilledCharacter() throws Exception;

  public IJasperReport createReport();

  public File createFile();
}