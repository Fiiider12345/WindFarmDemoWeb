<#-- @ftlvariable name="" type="sk.fri.uniza.views.NewDataView" -->
<!-- calls getDatas().getValue() and sanitizes it -->
<div class="section no-pad-bot" id="index-banner">
    <form class="container" action="/datas/new-data" method="post">
        <br><br>
        <div class="row">
            <div class="col s12">
                <div class="col s12 m4">
                    <h4>Datove informacie</h4>
                </div>
                <div class="col s12 m8">
                    <div class="card">
                        <div class="card-title orange lighten-1" style="padding: 10px 10px 0px 10px">
                            <div class="row valign-wrapper">
                                <div class="col s3 m4 l2">
                                    <img src="/assets/img/img_avatar.png" alt="" class="circle responsive-img">
                                    <!-- notice the "circle" class -->
                                </div>
                                <div class="col s9 m8 l10">
                                    <span class="white-text">
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                              <div class="input-field col s6">
                                    <input id="idDevice" name="idDevice" type="text" class="validate" required>
                                    <label for="idDevice">Device ID</label>
                                </div>
                            </div>
                         </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col s8 offset-s4 center-align">
                <button class="btn waves-effect waves-light orange" type="submit" name="action">Uložiť
                    <i class="material-icons right">send</i>
                </button>
            </div>
        </div>
        <#if toastMsg??>
        <script>
            // A $( document ).ready() block.
            $( document ).ready(function() {
                M.toast({html: '${toastMsg?no_esc}'});
            });

        </script>
        </#if>