$(function(){
	
	//加载的方法
	loading();
	//第二个下拉框(员工的名称)
    allEmpName();
    //提交
    submit();
    //获取单个客户的信息
    //oneClient();
});


function loading()
{
	$("#partName").empty().append("<option value='-10'>-- 请选择 --</option>");
	$("#empName").empty().append("<option value='-10'>-- 请选择 --</option>");
		$.ajax( {
		url : 'Emp_ajaxGetPartServlet',
		dataType : 'json',
		data : '',
		type : 'post',
		success : function(mydata) {
			$.each(mydata, function(index, xx) {
				$("#partName").append("<option value='" + xx.PId + "'>" + xx.PName + "</option>");
			});
		}
	});
};
function allEmpName()
{
	
		$(document).on('change','#partName',function() {
		
		var pid =$(this).val();
        		$.ajax( {
			url : '	Emp_ajaxGetEmpServlet',
			dataType : 'json',
			data : {
				pid : pid,
			},
			type : 'post',
			success : function(mydata) {
				$("#empName").empty().append("<option value='-10'>-- 请选择 --</option>");
				$.each(mydata, function(index, xx) {
					$("#empName").append("<option value='" + xx.EId + "'>" + xx.ETruename + "</option>");
				});
			}
		});
	})
};

function  submit()
{
	$(document).on('click','.btn',function(){
		
		var name=$("#cname").val();
		var tel=$("#ctel").val();
		var caddress=$("#caddress").val();
		var empEid=$("#empName").val();
		var pratid=$("#partName").val();
		var cid=$("#cid").val();
		
		if (name.length == 0) {
			$("#cname").addClass("newerror");
			$("#cname").focus();
			$partlayer=layer.tips('不能为空！！', '#cname', {
				tips : [ 2, 'red' ]
			});
			return false;
		}else if (tel.length == 0) {
			$("#ctel").addClass("newerror");
			$("#ctel").focus();
			$partlayer=layer.tips('不能为空！！', '#ctel', {
				tips : [ 2, 'red' ]
			});
			return false;
		}else if (caddress.length == 0) {
			$("#caddress").addClass("newerror");
			$("#caddress").focus();
			$partlayer=layer.tips('不能为空！！', '#caddress', {
				tips : [ 2, 'red' ]
			});
			return false;
		}else if (empEid==-10||pratid==-10) {
			if (empEid==-10) {
				$("#empName").addClass("newerror");
				$("#empName").focus();				
			}
			if (pratid==-10) {
				$("#partName").addClass("newerror");
				$("#partName").focus();				
			}
			layer.msg('请选择所属员工！', {
				icon : 2,
				time : 2000
			});
		}else{
		
		var mpp="client.CId="+cid+"&client.TEmp.EId="+empEid+"&client.CName="+name+"&client.CTel="+tel+"&client.CAddress="+caddress+"";
		
		$.post("Client_uppClientServlet",mpp,function(mydata)
			{
				var i = layer.load(0);
				if (mydata == 1) {
					parent.layer.msg('修改成功！', {
						icon : 6,
						time : 3000
					});
					var index = parent.layer.getFrameIndex(window.name); //获取窗口索引(真正的关 )
					parent.layer.close(index);	
				} else {
					parent.layer.msg('修改失败', 2, 9)
				}
			
			},'json')
			}
	})
	
};



