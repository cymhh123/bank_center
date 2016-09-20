/**
 * Created by Administrator on 2016/8/16.
 */
var baseVueObj = function (listUrl,editUrl,delUrl,pageFlag,func) {
    var initObj = {
        el: '#app',
        data: {
            list: [], //数据集合
            entity: {},//单个数据对象
            valid: {},//表单验证对象
            search: {},//查询条件对象
            extra: {},
            pageFlag:pageFlag,//是否开启分页
            oper: ""
        },
        ready: function () {
            if (func != null && func != undefined && typeof Function) {
                this.extra = func();
            }
            //获取数据
            this.getData(1);
            //初始化表单验证
            this.valid = $("#dataform").Validform({
                tiptype: 2
            });

        },
        methods: {
            getData: function (curr) { //获取数据
                this.oper = "find";
                var self = this;
                this.search.page = curr;
                $.getJSON(listUrl, this.search, function (res) {
                    self.list = res.extra;
                    if(self.pageFlag){
                        //显示分页
                        laypage({
                            cont: 'page', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
                            pages: res.pageTotal, //通过后台拿到的总页数
                            curr: curr || 1, //当前页
                            jump: function(obj, first){ //触发分页后的回调
                                if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
                                    self.getData(obj.curr);
                                }
                            }
                        });
                    }
                });
            },
            searchData: function () {//条件搜索
                this.oper = "find";
                this.getData(1);
            },
            editPage: function (index) {//编辑页面
                this.oper = "edit";
                if (this.list != null && this.list.length > 0) {
                    //复制一个对象
                    var b = {};
                    $.extend(b, this.list[index]);
                    this.entity = b;
                    console.log("entity:"+JSON.stringify(this.entity));
                    // if (func != null && func != undefined && typeof Function) {
                    //     this.extra = func();
                    // }
                    console.log("extra:"+JSON.stringify(this.extra));
                    //重置表单验证
                    this.valid.resetForm();
                    //重置后，文字消失不掉（bug）
                    $(".Validform_checktip").html("");
                    $("#modal").modal("show");
                }
            },
            addPage: function () {//添加页面
                this.oper = "add";
                //重置表单验证
                this.valid.resetForm();
                //重置后，文字消失不掉（bug）
                $(".Validform_checktip").html("");
                this.entity = {};//清空单个对象
                $("#modal").modal("show");
                // if (func != null && func != undefined && typeof Function) {
                //     this.extra = func();
                // };
                console.log("extra:" + JSON.stringify(this.extra));
            },
            commitData: function () {//提交数据
                this.oper = "commit";
                var self = this;
                console.log("commitData:"+JSON.stringify(this.entity));
                //表单验证
                var flag = this.valid.check();
                if (flag) {
                    $.ajax({
                        type: "POST",
                        url: editUrl,
                        data: this.entity,
                        success: function(res){
                            if(res.code == "200"){
                                //更新列表数据
                                self.getData(1);
                            }else{
                                alert(res.msg);
                            }
                            //清空保留的新增数据
                            self.entity = {};
                            $("#modal").modal("hide");
                        },
                        error: function(XMLHttpRequest){
                            console.log("Error: " + XMLHttpRequest.responseText)
                        }
                    });
                }
            },
            commitData2:function () {//表单提交
                this.oper = "commit";
                var self = this;
                var flag = this.valid.check();
                if(flag){
                    var data = new FormData($('#dataform')[0]);
                    $.ajax({
                        url: editUrl,
                        type: 'POST',
                        data: data,
                        dataType: 'JSON',
                        cache: false,
                        processData: false,
                        contentType: false,
                        success:function(res){
                            if(res.code == "200"){
                                self.getData(1);
                            }else{
                                alert("保存失败，服务器异常")
                            }
                            //清空保留的新增数据
                            self.entity = {};
                            $("#modal").modal("hide");
                        }
                    });
                }
            },
            deleteData: function (index) {//删除元素
                this.oper = "delete";
                var self = this;
                var id = this.list[index].id;
                $.post(delUrl, {id: id}, function (res) {
                    if (res.code == '200') {
                        //更新列表
                        self.getData(self.search.page)
                    }else{
                        alert("删除失败，服务器异常")
                    }
                })
            }
        }
    }
    return initObj;
};
