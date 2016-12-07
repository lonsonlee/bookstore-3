 $(function(){ 
	 /*
	  * 合并单元格
	  */
     mergeRows();  
});   


/**
 * 合并单元格
 */
function mergeRows() {  
          
       $(".table").rowspan(2); 
       $(".table").rowspan(3);  
       $(".table").rowspan(4);  
       $(".table").rowspan(5); 
}

/**
 * 删除订单
 */
function deleteOrder(oid){
	if(confirm("确定删除此订单?")){
		location=$("#basePath").val()+"order/deleteod.html?oid="+oid;
	}
}

/**
 * 取消订单
 */
function cancelOrder(oid){
	if(confirm("确定取消此订单?")){
		location=$("#basePath").val()+"order/cancelod.html?oid="+oid;
	}
}
       