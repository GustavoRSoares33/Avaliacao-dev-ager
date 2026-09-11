package br.com.soc.sistema.infra;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook; 
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import br.com.soc.sistema.vo.RelatorioVo;

public class GerarExcel {

	public InputStream exportarExcel(List<RelatorioVo> relatorios) throws Exception {
			
			Workbook workbook = new HSSFWorkbook(); 
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			
			Sheet sheet = workbook.createSheet("Compromissos");
			
			Row cabecalho = sheet.createRow(0);
			String[] colunas = {"Codigo do Funcionario", "Nome do Funcionario", "Codigo da Agenda", "Nome da Agenda", "Data", "Hora"};
			
			for (int i = 0; i < colunas.length; i++) {
				Cell cell = cabecalho.createCell(i);
				cell.setCellValue(colunas[i]);
			}
			
			SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm");

			int numeroLinha = 1;
			for (RelatorioVo r : relatorios) {
				Row linha = sheet.createRow(numeroLinha++);
				
				if (r.getFuncionario() != null && r.getFuncionario().getRowid() != null) {
					linha.createCell(0).setCellValue(r.getFuncionario().getRowid());
					linha.createCell(1).setCellValue(r.getFuncionario().getNome());
				} else {
					linha.createCell(0).setCellValue("Nao atribuido");
					linha.createCell(1).setCellValue("Nao atribuido");
				}

				if (r.getAgenda() != null && r.getAgenda().getRowid() != null) {
					linha.createCell(2).setCellValue(r.getAgenda().getRowid());
					linha.createCell(3).setCellValue(r.getAgenda().getNome());
				} else {
					linha.createCell(2).setCellValue("Nao atribuido");
					linha.createCell(3).setCellValue("Nao atribuido");
				}
				
				if (r.getCompromisso() != null && r.getCompromisso().getDataCompromisso() != null) {
					linha.createCell(4).setCellValue(formatData.format(r.getCompromisso().getDataCompromisso()));
				} else {
					linha.createCell(4).setCellValue("Nao atribuido");
				}
				
				if (r.getCompromisso() != null && r.getCompromisso().getHoraCompromisso() != null) {
					linha.createCell(5).setCellValue(formatHora.format(r.getCompromisso().getHoraCompromisso()));
				} else {
					linha.createCell(5).setCellValue("Nao atribuido");
				}
			}
			
			for (int i = 0; i < colunas.length; i++) {
				sheet.autoSizeColumn(i);
			}
			
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	
}