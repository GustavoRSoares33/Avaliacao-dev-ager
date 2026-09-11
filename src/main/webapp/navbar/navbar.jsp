<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
  <div class="container">
    <a class="navbar-brand" href="#">Sistema SOC</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>
    
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <s:url action="todosFuncionarios" var="linkFuncionarios"/>
          <a class="nav-link" href="${linkFuncionarios}">Funcionários</a>
        </li>
        <li class="nav-item">
          <s:url action="todasAgendas" var="linkAgendas"/>
          <a class="nav-link" href="${linkAgendas}">Agendas</a>
        </li>
        <li class="nav-item">
          <s:url action="todosCompromissos" var="linkCompromissos"/>
          <a class="nav-link" href="${linkCompromissos}">Compromissos</a>
        </li>
        <li class="nav-item">
          <s:url action="filtroRelatorios" var="linkRelatorios"/>
          <a class="nav-link" href="${linkRelatorios}">Gerar Relatório</a>
        </li>
      </ul>
    </div>
  </div>
</nav>