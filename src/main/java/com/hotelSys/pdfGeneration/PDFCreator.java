package com.hotelSys.pdfGeneration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

import com.hotelSys.model.ReservationWrapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFCreator {

  private final static String[] HEADER_ARRAY = {"Guest Name", "CheckIn Date", "CheckOut Date",
      "Price Per Night", "Invoice Value", "Room Size"};
  public final static Font SMALL_BOLD = new Font(Font.FontFamily.TIMES_ROMAN, 8,
      Font.BOLD);
  public final static Font NORMAL_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 8,
      Font.NORMAL);

  public static void addMetaData(Document document, String file) {

    document.addTitle("Reservation Invoice");

  }

  public static void addContent(Document document, ReservationWrapper reservationWrapper)
      throws DocumentException {
    Paragraph paragraph = new Paragraph();
    paragraph.setFont(NORMAL_FONT);
    createReportTable(paragraph, reservationWrapper);
    document.add(paragraph);
  }

  private static void createReportTable(Paragraph paragraph, ReservationWrapper reservationWrapper)
      throws BadElementException {
    PdfPTable table = new PdfPTable(7);
    table.setWidthPercentage(100);
    paragraph.add(new Chunk("Invoice Table :- ", SMALL_BOLD));
    if (null == reservationWrapper) {
      paragraph.add(new Chunk("No data to display."));
      return;
    }
    addHeaderInTable(HEADER_ARRAY, table);
    addToTable(table, reservationWrapper.getUserName());
    addToTable(table, reservationWrapper.getCheckIn());
    addToTable(table, reservationWrapper.getCheckOut());
    addToTable(table, String.valueOf((reservationWrapper.getPricePerNight())));
    addToTable(table, String.valueOf(reservationWrapper.getInvoiceValue()));
    addToTable(table, reservationWrapper.getRoomSize());

    paragraph.add(table);
  }

  public static void addTitlePage(Document document, String title) throws DocumentException {
    Paragraph preface = new Paragraph();
    addEmptyLine(preface, 3);
    preface.add(new Phrase("Reservation Invoice : ", NORMAL_FONT));
    preface.add(new Phrase(title, PDFCreator.NORMAL_FONT));
    addEmptyLine(preface, 1);
    preface.add(new Phrase("Date: ", PDFCreator.SMALL_BOLD));
    preface.add(new Phrase(new Date().toString(), PDFCreator.NORMAL_FONT));
    addEmptyLine(preface, 1);

    preface.setAlignment(Element.ALIGN_CENTER);
    document.add(preface);
    document.newPage();
  }

  public static void addEmptyLine(Paragraph paragraph, int number) {
    for (int i = 0; i < number; i++) {
      paragraph.add(new Paragraph(" "));
    }
  }

  public static void addHeaderInTable(String[] headerArray, PdfPTable table) {
    PdfPCell c1 = null;
    for (String header : headerArray) {
      c1 = new PdfPCell(new Phrase(header, PDFCreator.SMALL_BOLD));
      c1.setBackgroundColor(BaseColor.BLUE);
      c1.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(c1);
    }
    table.setHeaderRows(1);
  }

  public static void addToTable(PdfPTable table, String data) {
    table.addCell(new Phrase(data, PDFCreator.NORMAL_FONT));
  }

  public static Paragraph getParagraph() {
    Paragraph paragraph = new Paragraph();
    paragraph.setFont(PDFCreator.NORMAL_FONT);
    addEmptyLine(paragraph, 1);
    return paragraph;
  }

  public static void getPdfInvoice(ReservationWrapper reservationWrapper) {

    final String PDF_EXTENSION = ".pdf";
    String title = reservationWrapper.getUserName() + " Invoice";

    Document document = null;
    try {
      document = new Document(PageSize.A4);
      PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(
          new File(title + PDF_EXTENSION)));
      HeaderFooter event = new HeaderFooter();
      event.setHeader("Invoice");
      writer.setPageEvent(event);
      document.open();
      PDFCreator.addMetaData(document, title);
      PDFCreator.addTitlePage(document, title);
      PDFCreator.addContent(document, reservationWrapper);

    } catch (DocumentException e) {
      e.printStackTrace();
      System.out.println("FileNotFoundException occurs.." + e.getMessage());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      if (null != document) {
        document.close();
      }
    }
  }
}