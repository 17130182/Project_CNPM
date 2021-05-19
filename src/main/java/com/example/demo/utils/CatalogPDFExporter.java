package com.example.demo.utils;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.example.demo.entity.Catalog;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class CatalogPDFExporter {
	private List<Catalog> listCatalog;

	public CatalogPDFExporter(List<Catalog> listCatalog) {

		this.listCatalog = listCatalog;
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(11);
		Font font= FontFactory.getFont(FontFactory.COURIER);
		font.setColor(Color.WHITE);
		cell.setPhrase(new Phrase("Ma DM"));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Ten DM"));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Danh Muc Cha"));
		table.addCell(cell);
	
		
	}

	private void writeTableData(PdfPTable table) {
        for(Catalog catalog :listCatalog) {
        	table.addCell(String.valueOf(catalog.getId()));
        	table.addCell(String.valueOf(catalog.getName()));
        	table.addCell(String.valueOf(catalog.getParent_id()));
        
        }
	}

	public void exportpdf(HttpServletResponse response) throws DocumentException, IOException {
		
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		document.add(new Paragraph("Danh sach tat ca danh muc"));
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100);
		table.setSpacingBefore(15);
		table.setWidths(new float[] {5.0f,5.0f,5.0f});
		writeTableHeader(table);
		writeTableData(table);
		document.add(table);
		document.close();
	}
}
