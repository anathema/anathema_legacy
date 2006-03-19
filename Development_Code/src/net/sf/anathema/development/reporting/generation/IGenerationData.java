package net.sf.anathema.development.reporting.generation;

import java.io.File;

import net.sf.anathema.framework.reporting.IReport;
import net.sf.anathema.framework.repository.IItem;

public interface IGenerationData {

  public IItem createFilledCharacter() throws Exception;

  public IReport createReport();

  public File createFile();
}