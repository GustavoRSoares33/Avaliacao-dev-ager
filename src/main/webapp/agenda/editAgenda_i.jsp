<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><s:text name="label.agenda.editar"/></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	<body class="bg-secondary">

		<div class="container">
			<s:form action="/alterarAgendas.action">

				<div class="card mt-5">
					<div class="card-header">
						<div class="row">
							<div class="col-sm-5">
								<s:url action="todasAgendas" var="todos"/>
								<a href="${todos}" class="btn btn-success" >
									<s:text name="label.agendas"/>
								</a>
							</div>
							
							<div class="col-sm">
								<h5 class="card-title">
									<s:text name="label.agenda.editar"/>
								</h5>
							</div>
						</div>
					</div>
					
					<div class="card-body">
						<s:if test="hasActionErrors()">
					        <div class="alert alert-danger alert-dismissible fade show" role="alert">
					            <s:iterator value="actionErrors">
					                <strong>
					                	<s:text name="label.atencao"/>
					                </strong> 
					                <s:property/><br/>
					            </s:iterator>
					            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
					        </div>
					    </s:if>
						<div class="row align-items-center">
							<label for="id" class="col-sm-1 col-form-label text-center">
								<s:text name="label.codigo"/>
							</label>	

							<div class="col-sm-2">
								<s:textfield cssClass="form-control" id="id" name="agendaVo.rowid" readonly="true"/>							
							</div>	
						</div>
						
						<div class="row align-items-center mt-3">
							<label for="nome" class="col-sm-1 col-form-label text-center">
								<s:text name="label.nome"/>
							</label>	

							<div class="col-sm-5">
								<s:textfield cssClass="form-control" id="nome" name="agendaVo.nome"/>							
							</div>	
						</div>
						
						<div class="row align-items-center mt-3">
						    <label for="periodo" class="col-sm-1 col-form-label text-center">
						        <s:text name="label.periodo"/>
						    </label>	
						
						    <div class="col-sm-5">
						        <s:select 
						            cssClass="form-select" 
						            id="periodo" 
						            name="AgendaVo.periodoDisponivel" 
						            list="listaPeriodos" 
						            listKey="name()" 
						            listValue="descricao" 
						            headerKey="" 
						            headerValue="%{getText('label.selecione')}" 
						        />							
						    </div>	
						</div>
					</div>

					<div class="card-footer">
						<div class="form-row">
							<button class="btn btn-primary col-sm-4 offset-sm-1">
								<s:text name="label.salvar"/>
							</button>
							<button type="reset" class="btn btn-secondary col-sm-4 offset-sm-2">
								<s:text name="label.limpar.formulario"/>
							</button>
						</div>
					</div>
				</div>
			</s:form>
		</div>
		
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
	</body>
</html>