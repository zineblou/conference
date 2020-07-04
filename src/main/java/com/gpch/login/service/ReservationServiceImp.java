package com.gpch.login.service;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.gpch.login.repository.Reservation;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


@Service
public class ReservationServiceImp implements ReservationService{

	@Override
	public boolean creatPdf(Reservation reservation, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		

		Document document = new Document(PageSize.A4, 15, 15, 45, 30) ;
		
		String filePath = context.getRealPath("/resources/reports");
		File file = new File(filePath);
		boolean exists = new File(filePath).exists();
		if(!exists)
		{
			System.out.println(filePath);
			new File(filePath).mkdirs();
		}
		
		try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file+"/"+"reservation"+".pdf"));
			document.open();
			Font mainFont = FontFactory.getFont("Arial",10,BaseColor.BLACK);
			Paragraph paragraph = new Paragraph("Reservation",mainFont);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setIndentationLeft(50);
			paragraph.setIndentationRight(50);
			paragraph.setSpacingBefore(50);
			paragraph.setSpacingAfter(10);
			document.add(paragraph);
			PdfPTable table = new PdfPTable(2);
			table.setWidthPercentage(100);
			table.setSpacingAfter(10);
			table.setSpacingBefore(10);
			Font tableHeader = FontFactory.getFont("Arial",10,BaseColor.BLACK);
			Font tableBody = FontFactory.getFont("Arial",9,BaseColor.BLACK);
			float[] columnWidths = {2f,2f};
			table.setWidths(columnWidths);
			
			PdfPCell firstName = new PdfPCell(new Paragraph("Nom",tableHeader));
			firstName.setPaddingLeft(10);
			firstName.setHorizontalAlignment(Element.ALIGN_CENTER);
			firstName.setVerticalAlignment(Element.ALIGN_CENTER);
			firstName.setExtraParagraphSpace(5f);
			table.addCell(firstName);
			

			PdfPCell firstNameValue = new PdfPCell(new Paragraph(reservation.getReservateur().getName(),tableHeader));
			firstNameValue.setPaddingLeft(10);
			firstNameValue.setHorizontalAlignment(Element.ALIGN_CENTER);
			firstNameValue.setVerticalAlignment(Element.ALIGN_CENTER);
			firstNameValue.setExtraParagraphSpace(5f);
			table.addCell(firstNameValue);
			

			PdfPCell lastName = new PdfPCell(new Paragraph("Prenom",tableHeader));
			lastName.setPaddingLeft(10);
			lastName.setHorizontalAlignment(Element.ALIGN_CENTER);
			lastName.setVerticalAlignment(Element.ALIGN_CENTER);
			lastName.setExtraParagraphSpace(5f);
			table.addCell(lastName);
			

			PdfPCell lastNameValue = new PdfPCell(new Paragraph(reservation.getReservateur().getLastName(),tableHeader));
			lastNameValue.setPaddingLeft(10);
			lastNameValue.setHorizontalAlignment(Element.ALIGN_CENTER);
			lastNameValue.setVerticalAlignment(Element.ALIGN_CENTER);
			lastNameValue.setExtraParagraphSpace(5f);
			table.addCell(lastNameValue);
			
			PdfPCell idRes = new PdfPCell(new Paragraph("ID reservation",tableHeader));
			idRes.setPaddingLeft(10);
			idRes.setHorizontalAlignment(Element.ALIGN_CENTER);
			idRes.setVerticalAlignment(Element.ALIGN_CENTER);
			idRes.setExtraParagraphSpace(5f);
			table.addCell(idRes);
			
			PdfPCell idResValue = new PdfPCell(new Paragraph(reservation.getIdReservation().toString(),tableHeader));
			idResValue.setPaddingLeft(10);
			idResValue.setHorizontalAlignment(Element.ALIGN_CENTER);
			idResValue.setVerticalAlignment(Element.ALIGN_CENTER);
			idResValue.setExtraParagraphSpace(5f);
			table.addCell(idResValue);
			
			PdfPCell idSalle = new PdfPCell(new Paragraph("ID Salle",tableHeader));
			idSalle.setPaddingLeft(10);
			idSalle.setHorizontalAlignment(Element.ALIGN_CENTER);
			idSalle.setVerticalAlignment(Element.ALIGN_CENTER);
			idSalle.setExtraParagraphSpace(5f);
			table.addCell(idSalle);
			
			PdfPCell idSalleValue = new PdfPCell(new Paragraph(reservation.getSalleReservee().getId().toString(),tableHeader));
			idSalleValue.setPaddingLeft(10);
			idSalleValue.setHorizontalAlignment(Element.ALIGN_CENTER);
			idSalleValue.setVerticalAlignment(Element.ALIGN_CENTER);
			idSalleValue.setExtraParagraphSpace(5f);
			table.addCell(idSalleValue);
			
			PdfPCell debut= new PdfPCell(new Paragraph("date de debut",tableHeader));
			debut.setPaddingLeft(10);
			debut.setHorizontalAlignment(Element.ALIGN_CENTER);
			debut.setVerticalAlignment(Element.ALIGN_CENTER);
			debut.setExtraParagraphSpace(5f);
			table.addCell(debut);
			
			PdfPCell debutValue = new PdfPCell(new Paragraph(reservation.getDebutReservation().toString(),tableHeader));
			debutValue.setPaddingLeft(10);
			debutValue.setHorizontalAlignment(Element.ALIGN_CENTER);
			debutValue.setVerticalAlignment(Element.ALIGN_CENTER);
			debutValue.setExtraParagraphSpace(5f);
			table.addCell(debutValue);
			
			PdfPCell fin= new PdfPCell(new Paragraph("date de fin",tableHeader));
			fin.setPaddingLeft(10);
			fin.setHorizontalAlignment(Element.ALIGN_CENTER);
			fin.setVerticalAlignment(Element.ALIGN_CENTER);
			fin.setExtraParagraphSpace(5f);
			table.addCell(fin);
			
			PdfPCell finValue = new PdfPCell(new Paragraph(reservation.getFinReservation().toString(),tableHeader));
			finValue.setPaddingLeft(10);
			finValue.setHorizontalAlignment(Element.ALIGN_CENTER);
			finValue.setVerticalAlignment(Element.ALIGN_CENTER);
			finValue.setExtraParagraphSpace(5f);
			table.addCell(finValue);
			
			
			document.add(table);
			
			document.close();
			writer.close();

		
			
			
			
			
			
			
			
			return true;
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	
	}
	

}
