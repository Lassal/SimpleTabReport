package br.lassal.report.text;

public class ReportInfo {

	private boolean reportTitlePrinted;
	private int reportRowsPrinted;
	private int pageRowSize;
	private int pageHeaderRowSize;
	private int pageFooterRowSize;
	
	private int currentPage;
	private int currentPageRowsPrinted;
	private boolean currentPageHeaderPrinted;
	private boolean currentPageFooterPrinted;
	
	public ReportInfo(int pageRowSize, int pageHeaderRowSize, int pageFooterRowSize) {
		this.pageRowSize = pageRowSize;
		this.pageHeaderRowSize = pageHeaderRowSize;
		this.pageFooterRowSize = pageFooterRowSize;
		
		this.reportTitlePrinted = false;
		this.currentPage = 0;
		this.currentPageRowsPrinted = 0;
		this.currentPageHeaderPrinted = false;
		this.currentPageFooterPrinted = false;
	}
	
	public int getReportRowsPrinted() {
		return reportRowsPrinted;
	}

	public void setReportRowsPrinted(int reportRowsPrinted) {
		this.reportRowsPrinted = reportRowsPrinted;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCurrentPageRowsPrinted() {
		return currentPageRowsPrinted;
	}

	public void setCurrentPageRowsPrinted(int currentPageRowsPrinted) {
		this.currentPageRowsPrinted = currentPageRowsPrinted;
	}

	public boolean isCurrentPageHeaderPrinted() {
		return currentPageHeaderPrinted;
	}

	public void setCurrentPageHeaderPrinted(boolean currentPageHeaderPrinted) {
		this.currentPageHeaderPrinted = currentPageHeaderPrinted;
	}

	public boolean isCurrentPageFooterPrinted() {
		return currentPageFooterPrinted;
	}

	public void setCurrentPageFooterPrinted(boolean currentPageFooterPrinted) {
		this.currentPageFooterPrinted = currentPageFooterPrinted;
	}

	public boolean isReportTitlePrinted() {
		return reportTitlePrinted;
	}

	public void setReportTitlePrinted() {
		this.reportTitlePrinted = true;
	}


	public boolean isNewPage() {
		return this.getCurrentPageRowsPrinted() == 0;
	}
	
	public void registerRowWriten() {
		this.currentPageRowsPrinted++;
		this.reportRowsPrinted++;
	}
	
	public boolean fitInPage(int numberOfRows) {
		return (this.getCurrentPageRowsPrinted() + numberOfRows + this.getCurrentPageFooterSize()) <= this.pageRowSize;
	}
	
	public int getAvailableRows(boolean considerFooter) {
		if(considerFooter) {		
			return this.pageRowSize - this.currentPageRowsPrinted - this.getCurrentPageFooterSize() ;
		}
		else {
			return this.pageRowSize - this.currentPageRowsPrinted;
		}
	}
	
	private int getCurrentPageFooterSize() {
		return this.currentPageFooterPrinted ? 0 : this.pageFooterRowSize;
	}
	
	public int changeToNextPage() {
		this.currentPageRowsPrinted = 0;
		this.currentPageHeaderPrinted = false;
		this.currentPageFooterPrinted = false;
		
		return ++this.currentPage;
	}
	
	public boolean isFirstPage() {
		return this.currentPage == 0;
	}
	
	public int getEmptyLinesBeforeFooter() {
		return this.pageRowSize - this.getCurrentPageRowsPrinted() - this.getCurrentPageFooterSize();
	}
}
