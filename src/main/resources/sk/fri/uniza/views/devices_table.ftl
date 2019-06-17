<#-- @ftlvariable name="" type="sk.fri.uniza.views.DevicesView" -->
<!-- calls getDevices().getValue() and sanitizes it -->
<div class="section no-pad-bot" id="index-banner">
    <div class="container">
        <table id="example" class="striped" style="width:100%">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nazov</th>
                <th>Obsah</th>
             </tr>
            </thead>
            <tbody>
            <#list getDevices() as device>
                <tr>
                    <td>
                        ${device.id}
                    </td>
                    <td>
                        ${device.name}
                    </td>
                    <td>
                        ${device.content}
                    </td>
                    <td>
                        <a href="/devices/device-info?id=${device.getId()}" class="btn waves-effect waves-light grey"
                           name="action">
                            <i class="material-icons">edit</i>
                        </a>
                        <a onclick="onDelete('/devices/device-delete?id=${device.getId()}&page=${paged.page}')"
                           class="btn waves-effect waves-light red " name="action">
                            <i class="material-icons">delete_forever</i>
                        </a>
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
<!-- Modal Trigger -->
<#--<a class="waves-effect waves-light btn modal-trigger" href="#modal1">Modal</a>-->

<!-- Modal Structure -->
<div id="modal1" class="modal">
    <div class="modal-content">
        <h4>Chcete vymazať zariadenie?</h4>
        <p></p>
    </div>
    <div class="modal-footer">
        <a href="#!" class="modal-close waves-effect waves-red btn-flat">Nie</a>
        <a id="modal_href" href="#!" class="modal-close waves-effect waves-green btn-flat">Áno</a>
    </div>
</div>

<script>
    function onDelete(url) {
        $("#modal_href").attr("href", url);
        // var elem = document.querySelector("#modal1")
        // var instance = M.Modal.getInstance(elem);
        // instance.open();
        //
        var elem = document.querySelector("#modal1");
        var instance = M.Modal.init(elem);

        instance.open();


    }
</script>