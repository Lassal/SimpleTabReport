package br.lassal.report.text;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TextReportWriterTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createPaginatedReport() throws Exception {
		File outputFile = new File("C:\\Users\\lucianos\\cit\\workspace\\outputReports\\report1.txt");
		
		TextReportOptions options = new TextReportOptions();
		options.setColumns(130);
		options.setRows(10);
		options.setTitle(">>>>>> O TITULO <<<<<<");
		
		TextReportWriter writer = null;
		
		// Imprimir Pagina com header e footer
		
		String[] header = new String[3];
		String[] footer = new String[2];
		
		header[0] = "-------------------------------------------";
		header[1] = "======            HEADER              =====";
		header[2] = "-------------------------------------------";
		
		footer[0] = "[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[";
		footer[1] = "                 - END -                    ";
		
		try {
			writer = new TextReportWriter(outputFile, options, header, footer);
			
			writer.writeText(new String[]{
					"First line: AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa",
					"Second line: BBBBBBBBBBBBBB .. .. . .. . . dkdkd",
					"Third line: CADADDAD ADD ddddd ----------  dsls "
					});
			
			
			
		}
		finally {
			writer.close();
		}

		
		
	
	}

}
