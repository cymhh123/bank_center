<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/include/taglib.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <%@ include file="/WEB-INF/include/head.jsp" %>
    <link href="${ctxStatic}/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
    <title>后台管理系统</title>
</head>
<body>
<div class="header"></div>
<div class="loginWraper">
    <div id="loginform" class="loginBox">
        <form id="login" class="form form-horizontal">
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
                <div class="formControls col-xs-8">
                    <input id="account" name="account" type="text" placeholder="账号" class="input-text size-L" datatype="*1-20" nullmsg="请输入账号">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
                <div class="formControls col-xs-8">
                    <input id="password" name="password" type="password" placeholder="密码" class="input-text size-L" datatype="*1-20" nullmsg="请输入密码">
                </div>
            </div>
        </form>
        <div class="row cl">
            <div class="col-xs-8 col-xs-offset-3">
                <button class="btn btn-success radius size-L" onclick="login_in()">&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;</button>
            </div>
        </div>
    </div>
</div>
<div class="footer">明道致远网络科技公司</div>
<script type="text/javascript">
    var valid = $("#login").Validform();
    //登录
    function login_in() {
        var flag = this.valid.check();
        if(flag){
            var params = $("#login").serialize();
            $.ajax({
                url:"${ctxyh}/login_valid",
                type:"POST",
                data:params,
                success:function(res){
                    if(res.code == "200"){
                        window.location.href = "${ctxyh}/index";
                    }else{
                        alert("用户名或密码错误")
                    }
                }
            })
        }
    }
</script>
</body>
</html>
