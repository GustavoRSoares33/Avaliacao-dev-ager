<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Detalhes da Agenda</title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	<body class="bg-secondary">	
		<div class="container">
			<jsp:include page="/navbar/navbar.jsp" />
			
			<div class="row mt-4">
				<div class="col-sm p-0">
					<s:if test="hasActionErrors()">
						<div class="alert alert-danger alert-dismissible fade show" role="alert">
							<s:iterator value="actionErrors">
								<strong>Atenção:</strong> <s:property/><br/>
						     </s:iterator>
						     <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
						</div>
					</s:if>
				</div>
			</div>

			<div class="card shadow-sm mb-4">
				<div class="card-header bg-dark text-white d-flex justify-content-between align-items-center">
					<h5 class="mb-0">
						<strong>Agenda:</strong> ${agendaVo.nome} 
					</h5>
					<span class="badge bg-light text-dark fs-6">Período: ${agendaVo.periodoDisponivel.descricao}</span>
				</div>
				
				<div class="card-body bg-light">
					<h6 class="card-subtitle mb-3 text-muted">Agendar Novo Compromisso nesta sala:</h6>
					
					<s:form action="/novoCompromissos.action" cssClass="row g-3 align-items-end">
						<input type="hidden" name="telaAtual" value="telaAgenda" />
						<s:hidden name="compromissoVo.agenda.rowid" value="%{agendaVo.rowid}" />
						
						<div class="col-md-3">
							<label class="form-label fw-bold">Nome do Compromisso</label>
							<s:textfield cssClass="form-control" name="compromissoVo.nome" placeholder="Ex: Consulta médica"/>
						</div>
						
						<div class="col-md-3">
							<label class="form-label fw-bold">Funcionário Responsável</label>
							<s:select  
					            cssClass="form-select" 
					            name="compromissoVo.funcionario.rowid" 
					            list="funcionarios"  
					            headerKey=""  
					            headerValue="Selecione (Opcional)..." 
					            listKey="rowid" 
					            listValue="nome"								
					        />
						</div>
						
						<div class="col-md-2">
							<label class="form-label fw-bold">Data</label>
							<s:textfield type="date" cssClass="form-control" name="dataDigitada"/>
						</div>
						
						<div class="col-md-2">
							<label class="form-label fw-bold">Hora</label>
							<s:textfield type="time" cssClass="form-control" name="horaDigitada"/>
						</div>
						
						<div class="col-md-2 d-grid">
							<button class="btn btn-success" type="submit">Agendar</button>
						</div>
					</s:form>
				</div>
			</div>
			
			<div class="row">
				<table class="table table-light table-striped align-middle shadow-sm">
					<thead>
						<tr class="table-dark">
							<th><s:text name="label.id"/></th>
							<th>Nome do Compromisso</th>
							<th>Funcionário</th>
							<th>Data</th>
							<th>Hora</th>
							<th class="text-end"><s:text name="label.acao"/></th>
						</tr>
					</thead>
					
					<tbody>
						<s:iterator value="compromissos" >
							<tr>
								<td>${rowid}</td>
								<td><strong>${nome}</strong></td>
								<td>${funcionario.nome}</td>
								<td><s:date name="dataCompromisso" format="dd/MM/yyyy" /></td>
								<td><s:date name="horaCompromisso" format="HH:mm" /></td>
								
								<td class="text-end">
									<s:url action="editarCompromissos" var="editar">
										<s:param name="compromissoVo.rowid" value="rowid"></s:param>
									</s:url>
									<a href="${editar}" class="btn btn-sm btn-warning text-white">
										<s:text name="label.editar"/>
									</a>

									<a href="#" class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#confirmarExclusao" onclick="excluirCompromisso('${rowid}')">
									    <s:text name="label.excluir"/>
									</a>
								</td>
							</tr>
						</s:iterator>
						
						<s:if test="compromissos.isEmpty()">
							<tr>
								<td colspan="6" class="text-center text-muted py-4">
									<em>Não há compromissos marcados para esta agenda ainda.</em>
								</td>
							</tr>
						</s:if>
					</tbody>
				</table>
			</div>
			
			<div class="row mt-3 mb-5">
				<div class="col">
					<s:url action="todasAgendas" var="voltar"/>
					<a href="${voltar}" class="btn btn-success">Voltar para Agendas</a>
				</div>
			</div>
			
		</div>
		
		<div  class="modal fade" id="confirmarExclusao" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title"><s:text name="label.modal.titulo"/></h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		      	<span><s:text name="label.modal.corpo"/></span>
		      </div>
		      <div class="modal-footer">
	        	<a class="btn btn-secondary" data-bs-dismiss="modal">
					<s:text name="label.nao"/>
				</a>
				<a id="btnSimExcluir" href="#" class="btn btn-primary" style="width: 75px;">
				    <s:text name="label.sim"/>
				</a>					
		      </div>
		    </div>	    
		  </div>
		</div>
		
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
		
		<script>
		    function excluirCompromisso(idCompromisso) {
		        var btnSim = document.getElementById('btnSimExcluir');
		        // Redireciona para a Action de exclusão passando o ID
		        btnSim.href = 'excluirCompromissos.action?compromissoVo.rowid=' + idCompromisso;
		    }
		</script>
	</body>
</html>