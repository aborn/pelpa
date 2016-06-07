<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="Access-Control-Allow-Origin" content="*"/>
    <meta http-equiv="X-UA-Compatible " content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <link rel='shortcut icon' type='image/x-icon' href='/assets/images/favicon.ico' />
    <title>pelpa</title>
<#include "/common/semantic.ftl">
</head>
<body>

<#include "/layout/navbar.ftl"/>

<div class="ui container ak-main-container">

    <div class="ui orange message" id="currentRunDiv">
    ${currentRun}
    </div>

    <div class="ui green message">
        <div class="ui green progress" data-percent="${percent}" id="buildProgress">
            <div class="bar">
                <div class="progress"></div>
            </div>
            <div class="label" id="buildProgressDesc">${percentDesc}</div>
        </div>
    </div>

    <div class="ui styled fluid accordion">
        <div class="title">
            <i class="dropdown icon"></i>
            还没开始的包有哪些?
        </div>
        <div class="content">
            <p class="transition hidden">${pkgReady}</p>
        </div>
        <div class="title">
            <i class="dropdown icon"></i>
            正在进行中的包有哪些?
        </div>
        <div class="content">
            <p>${pkgOnging}</p>
        </div>
        <div class="title">
            <i class="dropdown icon"></i>
            已经完成的包有哪些?
        </div>
        <div class="content">
            <p>${pkgFinished}</p>
        </div>
    </div>

<#if (unstarted?? && unstarted?size > 0)>
    <table class="ui red table">
        <thead>
        <tr>
            <th>包名</th>
            <th>下载状态</th>
            <th>构建状态</th>
            <th>开始时间</th>
            <th>结束时间</th>
        </tr>
        </thead>
        <tbody>
            <#list unstarted as item >
            <tr>
                <td>${item.pkgName}</td>
                <td>${item.fetchStatus}</td>
                <td>${item.buildStatus}</td>
                <td>${(item.startTime?string('yy-MM-dd hh:mm.SS'))!}</td>
                <td>${(item.endTime?string('yy-MM-dd hh:mm.SS'))!}</td>
            </tr>
            </#list>
        </tbody>
    </table>
</#if>


<#if (onging?? && onging?size > 0)>
    <table class="ui orange table">
        <thead>
        <tr>
            <th>包名</th>
            <th>下载状态</th>
            <th>构建状态</th>
            <th>开始时间</th>
            <th>结束时间</th>
        </tr>
        </thead>
        <tbody>
            <#list onging as item >
            <tr>
                <td>${item.pkgName}</td>
                <td>${item.fetchStatus}</td>
                <td>${item.buildStatus}</td>
                <td>${(item.startTime?string('yy-MM-dd hh:mm.SS'))!}</td>
                <td>${(item.endTime?string('yy-MM-dd hh:mm.SS'))!}</td>
            </tr>
            </#list>
        </tbody>
    </table>
</#if>

<#if (finished?? && finished?size > 0)>
    <table class="ui green table">
        <thead>
        <tr>
            <th>包名</th>
            <th>下载状态</th>
            <th>构建状态</th>
            <th>开始时间</th>
            <th>结束时间</th>
        </tr>
        </thead>
        <tbody>
            <#list finished as item >
            <tr>
                <td>${item.pkgName}</td>
                <td>${item.fetchStatus}</td>
                <td>${item.buildStatus}</td>
                <td>${(item.startTime?string('yy-MM-dd hh:mm.SS'))!}</td>
                <td>${(item.endTime?string('yy-MM-dd hh:mm.SS'))!}</td>
            </tr>
            </#list>
        </tbody>
    </table>
</#if>
</div>

<script type="text/javascript">
    $('.ui.accordion').accordion();
    $('#buildProgress').progress();

    function timer() {
        $.ajax({
            url : '/elpa/build/ajaxBuildStatus.json',
            type: "GET",
            data: {},
            dataType: 'json',
            success: function (data) {
                $('#buildProgress').attr('data-percent', data.percent);
                $('#buildProgress').progress({percent:data.percent});
                $('#buildProgressDesc').html(data.percentDesc);
                $('#currentRunDiv').html(data.currentRun);
            },
            error: function (jXHR, textStatus, errorThrown) {
                //alert(errorThrown);
                console.log("error");
            }
        });
        setTimeout("timer()",5000);
    }

    $(function(){
        timer()
    })
</script>

</body>
</html>
