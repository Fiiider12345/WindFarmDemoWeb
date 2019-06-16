<#-- @ftlvariable name="" type="sk.fri.uniza.views.DevicesView" -->
<!-- calls getDevices().getValue() and sanitizes it -->
<div class="section no-pad-bot" id="index-banner">
    <form class="container" action="/devices/device-info" method="post">
        <input name="id" type="number" hidden value="${device.getId()}">
        <br><br>