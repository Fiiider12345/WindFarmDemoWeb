<#-- @ftlvariable name="" type="sk.fri.uniza.views.DataView" -->
<!-- calls getDatas().getValue() and sanitizes it -->
<div class="section no-pad-bot" id="index-banner">
    <form class="container" action="/datas/data-info" method="post">
        <input name="id" type="number" hidden value="${data.getId()}">
        <br><br>