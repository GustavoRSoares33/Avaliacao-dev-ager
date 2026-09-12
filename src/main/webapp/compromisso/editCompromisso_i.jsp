<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><s:text name="label.compromisso.editar"/></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	<body class="bg-secondary">

		<div class="container">
			<s:form action="/alterarCompromissos.action">

				<div class="card mt-5">
					<div class="card-header">
						<div class="row">
							<div class="col-sm-5">
								<s:url action="todosCompromissos" var="todos"/>
								<a href="${todos}" class="btn btn-success" >
									<s:text name="label.compromissos"/>
								</a>
							</div>
							
							<div class="col-sm">
								<h5 class="card-title">
									<s:text name="label.compromisso.editar"/>
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
							<label for="id" class="col-sm-2 col-form-label text-center">
								<s:text name="label.codigo"/>
							</label>	

							<div class="col-sm-2">
								<s:textfield cssClass="form-control" id="id" name="compromissoVo.rowid" readonly="true"/>							
							</div>	
						</div>
						
						<div class="row align-items-center mt-3">
							<label for="nome" class="col-sm-2 col-form-label text-center">
								<s:text name="label.nome"/>
							</label>	

							<div class="col-sm-5">
								<s:textfield cssClass="form-control" id="nome" name="compromissoVo.nome"/>							
							</div>	
						</div>

						<div class="row align-items-center mt-3">
							<label class="col-sm-2 col-form-label text-center">
								<s:text name="label.agenda"/>
							</label>	

							<div class="col-sm-5">
								<s:select  
						            cssClass="form-select" 
						            name="compromissoVo.agenda.rowid" 
						            list="agendas"  
						            headerKey=""  
						            headerValue="%{getText('label.nao.atribuido')}" 
						            listKey="rowid" 
						            listValue="nome"								
						        />						
							</div>	
						</div>

						<div class="row align-items-center mt-3">
							<label class="col-sm-2 col-form-label text-center">
								<s:text name="label.funcionario"/>
							</label>	

							<div class="col-sm-5">
								<s:select  
						            cssClass="form-select" 
						            name="compromissoVo.funcionario.rowid" 
						            list="funcionarios"  
						            headerKey=""  
						            headerValue="%{getText('label.nao.atribuido')}" 
						            listKey="rowid" 
						            listValue="nome"								
						        />						
							</div>	
						</div>

						<div class="row align-items-center mt-3">
							<label class="col-sm-2 col-form-label text-center">
								<s:text name="label.data"/>
							</label>	

							<div class="col-sm-2">
								<s:textfield type="date" cssClass="form-control" name="dataDigitada"/>							
							</div>	
						</div>

						<div class="row align-items-center mt-3">
							<label class="col-sm-2 col-form-label text-center">
								<s:text name="label.hora"/>
							</label>	

							<div class="col-sm-2">
								<s:textfield type="time" cssClass="form-control" name="horaDigitada"/>							
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