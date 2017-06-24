/* 打开一个新页面：调用时不加第二个参数 add by tony
 * 'start','_self'*/
function formSubmit (url,sTarget){
    document.forms[0].target = sTarget;//target:在哪里展现新页面,_self:在当前页面展示.
    document.forms[0].action = url;		//相对路径
    document.forms[0].submit();			//表单元素提交
    return true;
}