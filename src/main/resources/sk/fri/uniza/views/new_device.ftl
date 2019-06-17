<#-- @ftlvariable name="" type="sk.fri.uniza.views.NewDeviceView" -->
<!-- calls getDevices().getValue() and sanitizes it -->
<div class="section no-pad-bot" id="index-banner">
    <form class="container" action="/devices/new-device" method="post">
        <br><br>
        <div class="row">
            <div class="col s12">
                <div class="col s12 m4">
                    <h4>Informacie o zariadeniach</h4>
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
                         <div class="card-content">
                            <div class="row">
                              <div class="input-field col s6">
                                    <input id="name" name="name" type="text" class="validate" required>
                                    <label for="name">Name</label>
                                </div>
                                <div class="input-field col s6">
                                    <input id="content" name="content" type="text" class="validate" required>
                                    <label for="content">Content</label>
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