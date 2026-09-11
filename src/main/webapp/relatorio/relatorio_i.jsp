<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Gerar Relatório</title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	<body class="bg-secondary">

		<div class="container">
			<jsp:include page="/navbar/navbar.jsp" />
			
			<s:form action="/gerarRelatorios.action">
				
				<div class="card mt-5">
					<div class="card-header">
						<div class="row">
							<div class="col-sm-5">
							</div>
							
							<div class="col-sm">
								<h5 class="card-title">Gerar Relatório</h5>
							</div>
						</div>
					</div>
					
					<div class="card-body">
						<s:if test="hasActionErrors()">
						        <div class="alert alert-danger alert-dismissible fade show" role="alert">
						            <s:iterator value="actionErrors">
						                <strong>Atenção:</strong> <s:property/><br/>
						            </s:iterator>
						            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
						        </div>
						</s:if>
						
						<div class="row align-items-center mt-3">
							<label class="col-sm-2 col-form-label text-center">
								Data Inicial:
							</label>	

							<div class="col-sm-3">
								<s:textfield type="date" cssClass="form-control" name="dataInicio"/>							
							</div>	
						</div>

						<div class="row align-items-center mt-3">
							<label class="col-sm-2 col-form-label text-center">
								Data Final:
							</label>	

							<div class="col-sm-3">
								<s:textfield type="date" cssClass="form-control" name="dataFim"/>							
							</div>	
						</div>
					</div>

					<div class="card-footer">
						<div class="form-row d-flex">
							<button type="submit" class="btn btn-primary col-sm-4 offset-sm-1">Gerar em Tela (HTML)</button>
							<button type="submit" name="formato" value="excel" class="btn btn-success col-sm-4 offset-sm-2">Baixar Planilha (XLSX)</button>
						</div>
					</div>
				</div>
			</s:form>
		</div>
		
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
	</body>
</html>