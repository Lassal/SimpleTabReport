package br.lassal.report.text;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class TextReportWriter extends FileWriter {

	private TextReportOptions reportOptions;
	private ReportInfo reportInfo;
	
	
	private String title;
	private String[] pageHeader;
	private String[] pageFooter;
	
	
	
	public TextReportWriter(File file, TextReportOptions options, String[] pageHeader, String[] pageFooter) throws Exception {
		super(file, false);
		
		if(options == null) {
			throw new Exception("Cannot create a TextReportWriter without a TextReportOptions");
		}
		
		this.pageHeader = pageHeader;
		this.pageFooter = pageFooter;
		int headerSize = this.pageHeader != null ? this.pageHeader.length : 0;
		int footerSize = this.pageFooter != null ? this.pageFooter.length : 0;
		
        this.reportInfo = new ReportInfo(options.getRows(), headerSize, footerSize);
		this.reportOptions = options;
	}


	private void writeInPage(String... section) throws IOException {
		
		if(this.reportInfo.isNewPage()) {
			if(!this.reportInfo.isReportTitlePrinted()) {
				this.writeReportTitle();
			}		
			this.writePageHeader();
		}
		
		// check if it fits in the current page
		if(!this.fitInCurrentPage(section) ) {
			this.closeCurrentPage();
			this.writePageHeader();
		}
		
		this.writeSection(section);
		
	}
	
	private void closeCurrentPage() {
		// TODO Auto-generated method stub
		
	}


	private void writeSection(String...section) throws IOException {
		if(section != null) {
			for(String line: section) {
				this.write(line + "\n");
				this.reportInfo.registerRowWriten();
			}
		}
	}
	
	private void writeReportTitle() throws IOException {
		this.writeSection(this.reportOptions.getTitle());
		this.reportInfo.setReportTitlePrinted();
		
	}


	private boolean fitInCurrentPage(String[] section) {
		int sectionSize = section != null ? section.length : 0;
		
		return this.reportInfo.fitInPage(sectionSize);
	}


	private void writePageHeader() throws IOException {
		
		if(this.pageHeader != null && this.pageHeader.length >0) {
			this.writeSection(this.pageHeader);
		}
		
	}




	/*
	 * writeFullReport
	 * startWriteReport
	 * writeDetail
	 * finishWriteReport
	 * 
	 * - Title
	 * - Page Header
	 * - Page footer
	 * 
	 * - WriteLine
	 * 
	 */
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String[] getPageHeader() {
		return this.pageHeader;
	}

	public void setPageHeader(String[] pageHeader) {
		this.pageHeader = pageHeader;
	}

	public String[] getPageFooter() {
		return this.pageFooter;
	}

	public void setPageFooter(String[] pageFooter) {
		this.pageFooter = pageFooter;
	}

	public void writeTitle(String string) throws IOException {
		
		this.write(string);
		
	}


	public void writeText(String...text) throws IOException {
		this.writeInPage(text);
	}
	

	
}
