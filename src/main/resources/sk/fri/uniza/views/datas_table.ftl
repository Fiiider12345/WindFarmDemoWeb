<#-- @ftlvariable name="" type="sk.fri.uniza.views.DatasView" -->
<!-- calls getDatas().getValue() and sanitizes it -->
<div class="section no-pad-bot" id="index-banner">
    <div class="container">
        <table id="example" class="striped" style="width:100%">
            <thead>
            <tr>
                <th>ID</th>
                <th>Hodnota</th>
                <th>Zariadenie</th>
                <th>cas</th>
            </tr>
            </thead>
            <tbody>
            <#list getDatas() as data>
                <tr>
                    <td>
                        ${data.id}
                    </td>
                    <td>
                        ${data.value}
                    </td>
                    <td>
                        ${data.idDevice}
                    </td>
                    <td>
                        ${data.dateOfStart}
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
        <ul class="pagination">
            <#if paged.prevPage?? >
                <li class="waves-effect"><a href="?page=${paged.prevPage}">
                        <i class="material-icons">chevron_left</i></a></li>
            <#else>
                <li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
            </#if>

            <#list 1..paged.lastPage as pageNum>
                <#if pageNum == paged.page>
                    <li class="active"><a href="?page=${pageNum}">${pageNum}</a></li>
                <#else>
                    <li class="waves-effect"><a href="?page=${pageNum}">${pageNum}</a></li>
                </#if>
            </#list>

            <#if paged.nextPage?? >
                <li class="waves-effect"><a href="?page=${paged.nextPage}">
                        <i class="material-icons">chevron_right</i></a></li>
            <#else>
                <li class="disabled"><a href="#!"><i class="material-icons">chevron_right</i></a></li>
            </#if>
        </ul>
    </div>
    <br><br>
</div>
