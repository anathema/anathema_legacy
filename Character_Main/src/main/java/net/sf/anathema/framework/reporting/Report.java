package net.sf.anathema.framework.reporting;

import net.sf.anathema.character.main.framework.item.Item;

import java.io.OutputStream;

public interface Report {

  boolean supports(Item item);

  void print(Item item, OutputStream stream) throws ReportException;
}
