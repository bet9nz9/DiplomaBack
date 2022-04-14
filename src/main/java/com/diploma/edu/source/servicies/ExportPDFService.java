package com.diploma.edu.source.servicies;

import com.diploma.edu.source.model.Logger;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ExportPDFService {

    List<Logger> loggerList;

    public ExportPDFService(List<Logger> loggerList) {
        this.loggerList = loggerList;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.YELLOW);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.BLACK);

        cell.setPhrase(new Phrase("UserId",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("User",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("KeyId",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Key Code",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("EntranceId",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Entrance",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Date and Time",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Message", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table){
        for(Logger logger : loggerList){

            if (logger.geteKey() == null){
                table.addCell("-");
                table.addCell("-");
                table.addCell("-");
                table.addCell("-");
            } else {
                table.addCell(logger.geteKey().getReferencedUser().getId().toString());
                table.addCell(logger.geteKey().getReferencedUser().getLastName());
                table.addCell(logger.geteKey().getId().toString());
                table.addCell(logger.geteKey().getKeyCode());
            }

            table.addCell(logger.getEntrance().getId().toString());
            table.addCell(logger.getEntrance().getName());
            table.addCell(logger.getDateAndTime().toString());
            table.addCell(logger.getMessage());
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A3);

        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.BLUE);
        font.setSize(18);

        Paragraph title = new Paragraph("Logger",font);
        document.add(title);

        PdfPTable table = new PdfPTable(8);
        table.setWidths(new float[]{5,5,5,5,5,5,5, 15});
        //table.setWidthPercentage(100);
        table.setSpacingBefore(15);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
