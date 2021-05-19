package com.example.demo.utils;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.example.demo.entity.Product;
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

public class UserPDFExporter {
	private List<Product> listProduct;

	public UserPDFExporter(List<Product> listProduct) {

		this.listProduct = listProduct;
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(11);
		Font font= FontFactory.getFont(FontFactory.COURIER);
		font.setColor(Color.WHITE);
		cell.setPhrase(new Phrase("Ma SP"));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Ma DM"));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Ten SP"));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Gia"));
		table.addCell(cell);
		cell.setPhrase(new Phrase("So Luong"));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Trang Thai"));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Mo Ta"));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Content"));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Giam Gia"));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Link Hinh"));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Ngay Tao"));
		table.addCell(cell);
		
		
	}

	private void writeTableData(PdfPTable table) {
        for(Product product :listProduct) {
        	table.addCell(String.valueOf(product.getId()));
        	table.addCell(String.valueOf(product.getCatalog()));
        	table.addCell(String.valueOf(product.getName()));
        	table.addCell(String.valueOf(product.getPrice()));
        	table.addCell(String.valueOf(product.getQty()));
        	table.addCell(String.valueOf(product.getStatus()));
        	table.addCell(String.valueOf(product.getDescription()));
        	table.addCell(String.valueOf(product.getContent()));
        	table.addCell(String.valueOf(product.getDiscount()));
        	table.addCell(String.valueOf(product.getImage_link()));
        	table.addCell(String.valueOf(product.getCreated()));
        }
	}

	public void exportpdf(HttpServletResponse response) throws DocumentException, IOException {
		
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		document.add(new Paragraph("Danh sach tat ca san pham"));
		PdfPTable table = new PdfPTable(11);
		table.setWidthPercentage(100);
		table.setSpacingBefore(15);
		table.setWidths(new float[] {1.5f,3.5f,3.5f,2.5f,2.0f,1.5f,3.5f,3.0f,3.0f,1.5f,1.5f});
		writeTableHeader(table);
		writeTableData(table);
		document.add(table);
		document.close();
	}

}
