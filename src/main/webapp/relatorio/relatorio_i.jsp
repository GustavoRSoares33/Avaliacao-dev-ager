<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>

<html>
<head>
    <meta charset="UTF-8">

```
<title>Relatório de Compromissos</title>

<link rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
```

</head>

<body>

<div class="container mt-4">

```
<div class="card">

    <div class="card-header">
        <h4>Relatório de Compromissos</h4>
    </div>

    <div class="card-body">

        <!-- FILTRO -->

        <s:form action="gerarRelatorio" method="post">

            <div class="row">

                <div class="col-md-4">

                    <div class="form-group">

                        <label for="dataInicio">
                            Data inicial
                        </label>

                        <s:textfield
                            name="dataInicio"
                            id="dataInicio"
                            cssClass="form-control"
                            type="date" />

                    </div>

                </div>

                <div class="col-md-4">

                    <div class="form-group">

                        <label for="dataFim">
                            Data final
                        </label>

                        <s:textfield
                            name="dataFim"
                            id="dataFim"
                            cssClass="form-control"
                            type="date" />

                    </div>

                </div>

                <div class="col-md-4 d-flex align-items-end">

                    <button type="submit"
                            class="btn btn-primary">
                        Gerar relatório
                    </button>

                </div>

            </div>

        </s:form>

        <hr>

        <!-- RESULTADO -->

        <s:if test="relatorios != null">

            <div class="table-responsive">

                <table class="table table-bordered table-striped">

                    <thead>

                        <tr>

                            <th>Código Funcionário</th>
                            <th>Nome Funcionário</th>
                            <th>Código Agenda</th>
                            <th>Nome Agenda</th>
                            <th>Data do Compromisso</th>
                            <th>Hora do Compromisso</th>

                        </tr>

                    </thead>

                    <tbody>

                        <s:iterator value="relatorios">

                            <tr>

                                <td>
                                    <s:property value="funcionario.rowid"/>
                                </td>

                                <td>
                                    <s:property value="funcionario.nome"/>
                                </td>

                                <td>
                                    <s:property value="agenda.rowid"/>
                                </td>

                                <td>
                                    <s:property value="agenda.nome"/>
                                </td>

                                <td>
                                    <s:date
                                        name="compromisso.dataCompromisso"
                                        format="dd/MM/yyyy"/>
                                </td>

                                <td>
                                    <s:date
                                        name="compromisso.horaCompromisso"
                                        format="HH:mm"/>
                                </td>

                            </tr>

                        </s:iterator>

                    </tbody>

                </table>

            </div>

            <s:if test="relatorios.isEmpty()">

                <div class="alert alert-info">
                    Nenhum compromisso encontrado para o período informado.
                </div>

            </s:if>

        </s:if>

    </div>

</div>
```

</div>

</body>
</html>
