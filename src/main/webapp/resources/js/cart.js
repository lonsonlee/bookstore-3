$(function(){
	
	calTotal();
	
	/*
	 * 设置全选按钮的事件
	 */
	$("#selectAll").click(function(){
		
		var bool=$("#selectAll").prop("checked");
		setCheckboxBtn(bool);
		
		setClearingBtn(bool);
		
		calTotal();
	});
	
	/*
	 * 设置复选框的事件
	 */
	$("input:checkbox[name='checkboxBtn']").click(function(){
		
		//条目复选框的数量
		var allNum=$(":checkbox[name='checkboxBtn']").length;
		//选中复选框的数量
		var selectNum=$(":checkbox[name='checkboxBtn']:checked").length;
		if(allNum==selectNum){//复选框全部选中
			
			//设置全选框为选中状态
			$("#selectAll").prop("checked",true);
			//设置结算按钮为有效状态
			setClearingBtn(true);
		}else if(selectNum==0){
			
			$("#selectAll").prop("checked",false);
			setClearingBtn(false);
		}else{
			$("#selectAll").prop("checked",false);
			setClearingBtn(true);
		}
		calTotal();
	});
	
	/*
	 * 减号按钮的click事件
	 */
	$(".minus").click(function(){
		var idIndex=$(this).attr("id").indexOf("f_");
		//获取cartItemId
		var cartItemId=$(this).attr("id").substring(0,idIndex);
		var quantity=Number($("#"+cartItemId+"Quantity").val())-1;
		if(quantity==0){
			deleteItem();
			return false;
		}
		sendUpdateQuantity(cartItemId,quantity);
	});
	
	/*
	 * 加号按钮的click事件
	 */
	$(".plus").click(function(){
		var idIndex=$(this).attr("id").indexOf("f_");
		//获取cartItemId
		var cartItemId=$(this).attr("id").substring(0,idIndex);
		var quantity=Number($("#"+cartItemId+"Quantity").val())+1;
		sendUpdateQuantity(cartItemId,quantity);
	});
	
});

function sendUpdateQuantity(cartItemId,quantity){
	$.ajax({
		url:$("#basePath").val()+"cart/updateQ.html",
		data:{cartItemId:cartItemId,quantity:quantity},
		type:"POST",
		dataType:"json",
		async:false,
		cache:false,
		success:function(data){
			$("#"+cartItemId+"Quantity").val(quantity);
			var currPrice=Number($("#"+cartItemId+"CurrPrice").text());
			$("#"+cartItemId+"Subtotal").text(myRound(currPrice*quantity,2));
			calTotal();
		}
	});
}

/**
 * 四舍五入
 * num:传入的浮点数
 * dp:需要保留的小数点位数
 */
function myRound(num,dp){
	var numStr=num+"";
	var dpIndex=numStr.indexOf('.');
	
	if(dpIndex<0){
		return num;
	}
	var dpLength=numStr.length-dpIndex-1;
	if(dpLength<=dp){
		return num;
	}else{
		var setter=Math.pow(10,dp);
		num*=setter;
		num=Math.round(num);
		return num/setter;
	}
}


/**
 * 计算总计
 */
function calTotal(){
	var total=0;
	$(":checkbox[name=checkboxBtn]:checked").each(function(){
		var id=$(this).val();
		var subtotal=$("#"+id+"Subtotal").text();
		total+=Number(subtotal);
	});
	$("#total").text(myRound(total,2));
}

/**
 * 设置条目的复选框
 */
function setCheckboxBtn(bool){
	$(":checkbox[name='checkboxBtn']").prop("checked",bool);
	
}

/**
 * 设置结算按钮的样式和超链接的有效性
 */
function setClearingBtn(bool){
	if(bool){
		$("#clearingBtn").removeClass("disabled").addClass("clearingBtn");
		$("#clearingBtn").unbind("click");
	}else{
		$("#clearingBtn").removeClass("clearingBtn").addClass("disabled");
		$("#clearingBtn").click(function(){return false;});
	}
}

/**
 * 单条删除
 */
function deleteItem(){
	if(confirm("确定删除商品?")){
		var str=$(".deleteItem").attr("id");
		var idIndex=str.indexOf("delete");
		var itemId=str.substring(0,idIndex);
		location=$("#basePath").val()+"cart/deleteItems.html?ids="+itemId;
	}
}

/**
 * 批量删除
 */
function deleteItems(){
	if(confirm("确定删除商品?")){
		var arr=getCheckedValueArray();
	
		location=$("#basePath").val()+"cart/deleteItems.html?ids="+arr;
	}
}

/**
 * 获取被选中的checkbox的value（CartItemid）
 */
function getCheckedValueArray(){
	var arr=new Array();
	$(":checkbox[name='checkboxBtn']:checked").each(function(){
		arr.push($(this).val());
	});
	
	return arr;
}

/**
 * 结算
 */
function clearing(){
	var arr=getCheckedValueArray();
	$("#cartItemIds").val(arr.toString());
	$("#hiddenTotal").val($("#total").text());
	$("#clearingForm").submit();
}