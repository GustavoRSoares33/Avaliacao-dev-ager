<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>
			<s:text name="label.relatorio.impressao"/>
		</title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	<body class="bg-secondary">
		
		<div class="container mt-4 mb-5">
			<div class="row mb-3 no-print">
				<div class="col-sm-6">
					<s:url action="filtroRelatorios" var="voltar"/>
					<a href="${voltar}" class="btn btn-light shadow-sm">
						<strong>
							<s:text name="label.relatorio.voltar"/>
						</strong>
					</a>
				</div>
			</div>

			<div class="card shadow">
				<div class="card-header bg-dark text-white text-center py-3">
					<h4 class="mb-0">
						<s:text name="label.relatorio.titulo"/>
					</h4>
				</div>
				
				<div class="card-body p-0">
					<table class="table table-striped table-hover align-middle mb-0">
						<thead class="table-secondary">
							<tr>
								<th><s:text name="label.relatorio.codigo.funcionario"/></th>
								<th><s:text name="label.relatorio.nome.funcionario"/></th>
								<th><s:text name="label.relatorio.codigo.agenda"/></th>
								<th><s:text name="label.relatorio.nome.agenda"/></th>
								<th><s:text name="label.data"/></th>
								<th><s:text name="label.hora"/></th>
							</tr>
						</thead>
						
						<tbody>
							<s:iterator value="relatorios">
								<tr>
									<td>
										<s:if test="funcionario.rowid != null && !funcionario.rowid.trim().isEmpty()">
											<s:property value="funcionario.rowid"/>
										</s:if>
										<s:else>
											<s:text name="label.nao.atribuido"/>
										</s:else>
									</td>
									<td>
										<s:if test="funcionario.nome != null && !funcionario.nome.trim().isEmpty()">
											<s:property value="funcionario.nome"/>
										</s:if>
										<s:else>
											<s:text name="label.nao.atribuido"/>
										</s:else>
									</td>
									
									<td>
										<s:if test="agenda.rowid != null && !agenda.rowid.trim().isEmpty()">
											<s:property value="agenda.rowid"/>
										</s:if>
										<s:else>
											<s:text name="label.nao.atribuido"/>
										</s:else>
									</td>
									<td>
										<s:if test="agenda.nome != null && !agenda.nome.trim().isEmpty()">
											<s:property value="agenda.nome"/>
										</s:if>
										<s:else>
											<s:text name="label.nao.atribuido"/>
										</s:else>
									</td>
									
									<td><s:date name="compromisso.dataCompromisso" format="dd/MM/yyyy" /></td>
									<td><s:date name="compromisso.horaCompromisso" format="HH:mm" /></td>
								</tr>
							</s:iterator>
							
							<s:if test="relatorios.isEmpty()">
								<tr>
									<td colspan="6" class="text-center py-5 text-muted">
										<em><s:text name="label.relatorio.sem.resultado"/></em>
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