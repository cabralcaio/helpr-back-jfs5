package org.soulcodeacademy.helpr.utils;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.soulcodeacademy.helpr.domain.Chamado;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PDFGenerator {
    //Precisamos listar os chamados
    private List<Chamado> listaChamado;

    public PDFGenerator(List<Chamado> listaChamado){
        this.listaChamado = listaChamado;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.gray);
        cell.setPadding(4);


        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setColor(Color.WHITE);
        fontTitle.setSize(11);


        cell.setPhrase(new Phrase("Id", fontTitle));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Título", fontTitle));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Descrição", fontTitle));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Cliente", fontTitle));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Funcionario", fontTitle));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Data de Abertura", fontTitle));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Status",fontTitle));
        table.addCell(cell);

    }
    private  void writeTableData(PdfPTable table){
        for (Chamado chamado : listaChamado){
            table.addCell(String.valueOf(chamado.getIdChamado()));
            table.addCell(chamado.getTitulo());
            table.addCell(chamado.getDescricao());
            table.addCell(String.valueOf(chamado.getCliente().getNome()));
            String nomeFuncionario = chamado.getFuncionario() == null ? "" : chamado.getFuncionario().getNome();
            table.addCell(String.valueOf(nomeFuncionario));
            table.addCell(String.valueOf(chamado.getDataAbertura()));
            table.addCell(String.valueOf(chamado.getStatus()));
        }
    }

    public  void export(HttpServletResponse response)throws  DocumentException, IOException{
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(24);
        font.setColor(Color.BLACK);


        Paragraph p = new Paragraph("Lista de chamados", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.3f, 4.5f, 4.5f, 4.5f, 4.5f, 4.5f,4.5f});
        table.setSpacingBefore(3);


        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();
    }
}
