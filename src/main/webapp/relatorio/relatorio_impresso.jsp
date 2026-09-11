<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Impressão - Relatório</title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	<body class="bg-secondary">
		
		<div class="container mt-4 mb-5">
			<div class="row mb-3 no-print">
				<div class="col-sm-6">
					<s:url action="filtroRelatorios" var="voltar"/>
					<a href="${voltar}" class="btn btn-light shadow-sm">
						<strong>Voltar aos Filtros</strong>
					</a>
				</div>
			</div>

			<div class="card shadow">
				<div class="card-header bg-dark text-white text-center py-3">
					<h4 class="mb-0">Relatório</h4>
				</div>
				
				<div class="card-body p-0">
					<table class="table table-striped table-hover align-middle mb-0">
						<thead class="table-secondary">
							<tr>
								<th>Codigo do Funcionario</th>
								<th>Nome do Funcionario</th>
								<th>Codigo da Agenda</th>
								<th>Nome da Agenda</th>
								<th>Data</th>
								<th>Hora</th>
							</tr>
						</thead>
						
						<tbody>
							<s:iterator value="relatorios">
								<tr>
									<td><s:property value="funcionario.rowid" default="Não atribuído" /></td>
									<td><s:property value="funcionario.nome" default="Não atribuído" /></td>
									
									<td><s:property value="agenda.rowid" default="Não atribuído" /></td>
									<td><s:property value="agenda.nome" default="Não atribuído" /></td>
									
									<td>
										<s:if test="compromisso.dataCompromisso != null">
											<s:date name="compromisso.dataCompromisso" format="dd/MM/yyyy" />
										</s:if>
										<s:else>Não atribuído</s:else>
									</td>
									
									<td>
										<s:if test="compromisso.horaCompromisso != null">
											<s:date name="compromisso.horaCompromisso" format="HH:mm" />
										</s:if>
										<s:else>Não atribuído</s:else>
									</td>
								</tr>
							</s:iterator>
							
							<s:if test="relatorios.isEmpty()">
								<tr>
									<td colspan="6" class="text-center py-5 text-muted">
										<em>Nenhum compromisso encontrado para o período selecionado.</em>
									</td>
								</tr>
							</s:if>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		
	</body>
</html>