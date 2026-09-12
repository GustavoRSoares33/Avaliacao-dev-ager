<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><s:text name="label.agenda.cadastradas"/></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	<body class="bg-secondary">	
		<div class="container">
			<jsp:include page="/navbar/navbar.jsp" />
			<div class="row mt-5 mb-2">
				<div class="col-sm p-0">
					<s:if test="hasActionErrors()">
						<div class="alert alert-danger alert-dismissible fade show" role="alert">
							<s:iterator value="actionErrors">
								<strong>
									<s:text name="label.atencao"/>
								</strong> <s:property/><br/>
						     </s:iterator>
						     <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
						</div>
					</s:if>
					<s:form action="/filtrarAgendas.action">
						<div class="input-group">
							<span class="input-group-text">
								<strong><s:text name="label.buscar.por"/></strong>
							</span>	
								<s:select  
						            cssClass="form-select" 
						            id="tipoFiltro"
						            name="filtrar.opcoesCombo" 
						            list="listaOpcoesCombo"  
						            headerKey=""  
						            headerValue="%{getText('label.escolha')}" 
						            listKey="%{codigo}" 
						            listValueKey="%{descricao}"
						            value="filtrar.opcoesCombo.codigo"	
						            onchange="alternarCamposBusca(true)"								
						        />
								
								<s:textfield cssClass="form-control" id="campoTexto" name="filtrar.valorBusca"/>
								
								<s:select  
						            cssClass="form-select d-none" 
						            id="campoSelect"
						            name="filtrar.valorBusca" 
						            list="listaPeriodos"  
						            headerKey=""  
						            headerValue="%{getText('label.selecione')}" 
						            listKey="codigo" 
						            listValue="descricao"
						            disabled="true"									
						        />
						        
							<button class="btn btn-primary" type="submit"><s:text name="label.pesquisar"/></button>
							<s:url action="todasAgendas" var="urlLimpar"/>
        					<a href="${urlLimpar}" class="btn btn-dark">
        						<s:text name="label.limpar"/>
        					</a>
						</div>
					</s:form>			
				</div>
			</div>
			
			<div class="row">
				<table class="table table-light table-striped align-middle">
					<thead>
						<tr>
							<th><s:text name="label.id"/></th>
							<th><s:text name="label.nome"/></th>
							<th><s:text name="label.periodo"/></th> 
							<th class="text-end mt-5"><s:text name="label.acao"/></th>
						</tr>
					</thead>
					
					<tbody>
						<s:iterator value="agendas" >
							<tr>
								<td>${rowid}</td>
								<td>${nome}</td>
								<td>${periodoDisponivel.descricao}</td>
								<td class="text-end">
									<s:url action="detalhesAgendas" var="abrir">
										<s:param name="agendaVo.rowid" value="rowid"></s:param>
									</s:url>
									
									<a href="${abrir}" class="btn btn-primary text-white">
										<s:text name="label.abrir"/>
									</a>
									
									<s:url action="editarAgendas" var="editar">
										<s:param name="agendaVo.rowid" value="rowid"></s:param>
									</s:url>

									<a href="${editar}" class="btn btn-warning text-white">
										<s:text name="label.editar"/>
									</a>

									<a href="#" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#confirmarExclusao" onclick="excluirAgenda('${rowid}')">
									    <s:text name="label.excluir"/>
									</a>
								</td>
							</tr>
						</s:iterator>
					</tbody>
					
					<tfoot class="table-secondary">
						<tr>
							<td colspan="4">
								<s:url action="novoAgendas" var="novo"/>
								
								<a href="${novo}" class="btn btn-success">
									<s:text name="label.novo"/>
								</a>
							</td>
						</tr>
					</tfoot>				
				</table>
			</div>

			<div class="row">
			
			</div>
		</div>
		
		<div  class="modal fade" id="confirmarExclusao" 
			data-bs-backdrop="static" data-bs-keyboard="false"
			tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title"><s:text name="label.modal.titulo"/></h5>
		        
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      
		      <div class="modal-body">
		      	<span><s:text name="label.modal.corpo"/></span>
		      </div>
		      
		      <div class="modal-footer">
	        	<a class="btn btn-secondary" data-bs-dismiss="modal" aria-label="Close">
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
		    function excluirAgenda(idAgenda) {

		        var btnSim = document.getElementById('btnSimExcluir');
		        
		        btnSim.href = 'excluirAgendas.action?agendaVo.rowid=' + idAgenda;
		    }

		    function alternarCamposBusca(limparCampos) {
		        var tipoFiltro = document.getElementById("tipoFiltro").value;
		        var campoTexto = document.getElementById("campoTexto");
		        var campoSelect = document.getElementById("campoSelect");

		        if (tipoFiltro === "3") {
		            campoTexto.classList.add("d-none");
		            campoTexto.disabled = true;

		            campoSelect.classList.remove("d-none");
		            campoSelect.disabled = false;
		        } else {
		            campoSelect.classList.add("d-none");
		            campoSelect.disabled = true;

		            campoTexto.classList.remove("d-none");
		            campoTexto.disabled = false;

		        }

		        if (limparCampos) {
		            campoTexto.value = "";
		            campoSelect.value = "";
		        }
		    }

		    document.addEventListener("DOMContentLoaded", function() {
		        alternarCamposBusca(false);
		    });
		    
		</script>
	</body>
</html>